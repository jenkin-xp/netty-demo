package com.xiaorui.socket.base.concurrent;

public abstract class AbstractHandler<T, V> implements IHandler<T, V>{

    protected T message;

    protected V param;

    @Override
    public abstract void doAction();

    @Override
    public void run() {
        doAction();
    }

    @Override
    public T getMessage() {
        return message;
    }

    @Override
    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public V getParam() {
        return param;
    }

    @Override
    public void setParam(V param) {
        this.param = param;
    }
}
