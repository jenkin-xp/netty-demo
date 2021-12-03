package com.xiaorui.socket.base.network.customer;

import com.xiaorui.socket.base.message.IMessage;
import io.netty.channel.Channel;

public interface INetworkConsumer {

    /**
     * 执行具体的指令
     *
     * @param message message 客户端发送的消息
     * @param channel channel 与客户端连接的管道
     */
    void consume(IMessage message, Channel channel);

}
