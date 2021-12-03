package com.xiaorui.socket.base.network.processor;

import com.xiaorui.socket.base.concurrent.IHandler;

public interface IProcessor {

    /**
     * 执行具体的指令
     * @param handler 具体的执行
     */
    void process(IHandler handler);

}
