package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.DeterministicExecutor;
import org.junit.Test;

import java.util.concurrent.Executor;

/**
 * Created by prasniths on 13/02/16.
 */
public class JmockMultiThreadedUnitTesting {
    Mockery context = new Mockery();


    @Test
    public void mockFailingMultiThreadedCode() throws Exception {
        final Alarm alarm = context.mock(Alarm.class);
        DeterministicExecutor executor = new DeterministicExecutor();
        Guard guard = new Guard(alarm, executor);
        Burgler burgler = context.mock(Burgler.class);
        context.checking(new Expectations() {
            {
                exactly(1).of(alarm).ring();
            }
        });

        guard.notice(burgler);
        executor.runUntilIdle();
        context.assertIsSatisfied();
    }
}
