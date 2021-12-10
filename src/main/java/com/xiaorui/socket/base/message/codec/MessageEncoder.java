package com.xiaorui.socket.base.message.codec;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.vo.RequestVO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageEncoder extends MessageToByteEncoder<IMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IMessage msg, ByteBuf out) throws Exception {
        if (null == msg) {
            log.warn("msg is null");
            return;
        }
        byte[] bodyBytes = msg.getBodyByte();
        out.writeInt(bodyBytes.length);
        out.writeBytes(bodyBytes);
    }

}
