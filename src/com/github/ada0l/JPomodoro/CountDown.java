package com.github.ada0l.JPomodoro;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown {

    private int interval;
    private Timer timer;
    private final CountDownAction action;
    private Task task;

    public CountDown(int i, CountDownAction a) {
        interval = i;
        action = a;
    }

    public void start() {
        timer = new Timer();
        task = new Task();
        action.onStart();
        timer.scheduleAtFixedRate(task,1000, 1000);
    }

    public int getInterval() {
        return interval;
    }

    public void cancel() throws NullPointerException {
        task.cancel();
    }

    public boolean isNull() {
        return (timer == null || task == null);
    }

    class Task extends TimerTask {

        @Override
        public void run() {
            action.onTick();
            if (interval == 1) {
                task.cancel();
                action.onDone();
            }
            interval--;
        }
    }
}
