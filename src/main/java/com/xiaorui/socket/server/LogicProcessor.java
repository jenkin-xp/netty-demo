package com.xiaorui.socket.server;

import com.xiaorui.socket.base.concurrent.IHandler;
import com.xiaorui.socket.base.network.processor.IProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消息处理进程
 *
 *
 */
public class LogicProcessor implements IProcessor {

    private static ExecutorService executor = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100000), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void process(IHandler handler) {
        executor.execute(handler);
    }
}
