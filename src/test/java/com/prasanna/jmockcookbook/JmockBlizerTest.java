package com.prasanna.jmockcookbook;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Blitzer;
import org.jmock.lib.concurrent.DeterministicExecutor;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by prasniths on 13/02/16.
 */
public class JmockBlizerTest {
    Mockery context = new JUnit4Mockery();

    @Test
    public void mockTestingGuardObjectUsingBlitzMethod() throws Exception {
        Alarm alarm = context.mock(Alarm.class);
        DeterministicExecutor executor = new DeterministicExecutor();

        final Guard guard = new Guard(alarm, executor);

        int actionCount = 25000;
        Blitzer blitzer = new Blitzer(actionCount);
        blitzer.blitz(new Runnable() {
            public void run() {
                guard.intrusionDetected();
            }
        });

        assertThat( guard.getIntrusionCount(),is(actionCount)  );

    }
}
