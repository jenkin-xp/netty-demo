package com.xiaorui.socket.base.concurrent;


import com.xiaorui.socket.base.concurrent.ICommand;

public interface IHandler<T, V> extends ICommand {

    /**
     * 消息
     * @return 消息request
     */
    T getMessage();

    /**
     * 设置消息request
     * @param message request
     */
    void setMessage(T message);

    /**
     * 参数
     * @return 参数
     */
    V getParam();

    /**
     *  设置参数
     * @param parm 参数
     */
    void setParam(V parm);
}
