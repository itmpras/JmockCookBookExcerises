package com.prasanna.jmockcookbook;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by prasniths on 13/02/16.
 */
public class Guard {

    final Alarm alarm;
    final Executor executor;

    public Guard(Alarm alarm) {
        this.alarm = alarm;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void notice(Burgler burgler) {

        executor.execute(startAlarmTask());

    }

    private Runnable startAlarmTask() {
        Runnable ringAlarmTask = new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alarm.ring();
                }
            }
        };

        return ringAlarmTask;
    }
}
