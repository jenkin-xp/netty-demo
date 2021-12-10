package com.xiaorui.socket.base.network.customer;

import com.xiaorui.socket.base.vo.RequestVO;
import io.netty.channel.Channel;

public interface INetworkConsumer {

    /**
     * 执行具体的指令
     *
     * @param requestVO message 客户端发送的消息
     * @param channel channel 与客户端连接的管道
     */
    void consume(RequestVO requestVO, Channel channel);

}
