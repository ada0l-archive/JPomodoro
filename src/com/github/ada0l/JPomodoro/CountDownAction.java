package com.github.ada0l.JPomodoro;

public interface CountDownAction {
    void onStart();
    void onTick();
    void onDone();
}
