package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.internal.State;
import org.jmock.lib.concurrent.DeterministicExecutor;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by prasniths on 13/02/16.
 */
public class JmockUsingSynchronizer {
    Mockery context = new Mockery();
    private Synchroniser threadingPolicy = new Synchroniser();
    Mockery threadSafeContext = new JUnit4Mockery() {
        {
            setThreadingPolicy(threadingPolicy);
        }
    };

    States states;

    @Before
    public void setUp() throws Exception {
        states = threadSafeContext.states("alarmState").startsAs("Intial");

    }

    @Test
    // Test failing because mock is used by multiple thread
    public void mockBeingPassedToMultipleThreadWitoutSynchroniser() throws Exception {
        testSnoozeAlarmWith(context);
    }


    @Test
    public void mockBeingPassedToMultipleThreadWithSynchroniser() throws Exception {
        testSnoozeAlarmWith(threadSafeContext);
    }

    private void testSnoozeAlarmWith(Mockery context) throws InterruptedException {
        final Alarm alarm = context.mock(Alarm.class);
        DeterministicExecutor executor = new DeterministicExecutor();
        final Guard guard = new Guard(alarm, executor);
        context.checking(new Expectations() {
            {
                oneOf(alarm).snooze();
                when(states.is("Intial"));
                then(states.is("snoozed"));
            }
        });

        guard.stopAlarmTask();
        threadingPolicy.waitUntil(states.is("snoozed"), 1000);
        context.assertIsSatisfied();
    }
}