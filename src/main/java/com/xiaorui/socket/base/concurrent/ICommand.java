package com.xiaorui.socket.base.concurrent;

public interface ICommand extends Runnable{

    void doAction();

    @Override
    default void run() {
        doAction();
    }
}
