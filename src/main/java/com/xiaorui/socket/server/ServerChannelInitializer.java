package com.xiaorui.socket.server;

import com.xiaorui.socket.base.constant.MessageValue;
import com.xiaorui.socket.base.message.codec.MessageDecoder;
import com.xiaorui.socket.base.message.codec.MessageEncoder;
import com.xiaorui.socket.hander.ServerHandler;
import com.xiaorui.socket.server.channel.websocket.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/3 19:07
 * @Version V1.0
 **/
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private StringDecoder stringDecoder;

    @Autowired
    private StringEncoder stringEncoder;

    @Autowired
    private ServerHandler serverHandler;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("encoder", new MessageEncoder());
//        pipeline.addLast("decoder", new MessageDecoder(MessageValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
//                MessageValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, MessageValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
//                MessageValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, MessageValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP, false,
//                MessageValue.MESSAGE_TYPE_BYTE));
//        pipeline.addLast("handler", serverHandler);
        pipeline.addLast("webSocketHandler", webSocketHandler);
    }

}

