package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

/**
 * Created by prasniths on 13/02/16.
 */
public class JmockMultiThreadedUnitTesting {
    Mockery context = new Mockery();


    @Test
    public void mockFailingMultiThreadedCode() throws Exception {
        final Alarm alarm = context.mock(Alarm.class);
        Guard guard = new Guard(alarm);
        Burgler burgler = context.mock(Burgler.class);
        context.checking(new Expectations() {
            {
                oneOf(alarm).ring();
            }
        });

        guard.notice(burgler);


    }
}
