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
    int intrusionCount;

    public Guard(Alarm alarm, Executor executor) {
        this.alarm = alarm;
        this.executor = executor;
    }

    public void notice(Burgler burgler) {
        startAlarmTask();
    }

    private void startAlarmTask() {
        Runnable ringAlarmTask = new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alarm.ring();
                }
            }
        };

        executor.execute(ringAlarmTask);
    }

    public void stopAlarmTask() throws InterruptedException {
        Runnable snoozeAlarm = new Runnable() {
            public void run() {
                alarm.snooze();

            }
        };


        Thread thread = new Thread(snoozeAlarm);
        thread.start();
    }

    public void intrusionDetected() {
        intrusionCount++;
    }

    public int getIntrusionCount() {
        return intrusionCount;
    }
}
