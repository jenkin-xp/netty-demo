package com.xiaorui.socket.base.message.codec;

import com.xiaorui.socket.base.message.IMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageEncoder extends MessageToByteEncoder<IMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IMessage msg, ByteBuf out) throws Exception {
        if (null == msg) {
            log.warn("msg is null");
            return;
        }
        //    String body = msg.getBody();
        //    byte[] bodyBytes = body.getBytes(Charset.forName(ConstantValue.PROJECT_CHARSET));
        byte[] bodyBytes = msg.getBodyByte();
        out.writeShort(msg.getMessageId());
        out.writeShort(msg.getStatusCode());
        out.writeInt(bodyBytes.length);
        out.writeBytes(bodyBytes);
    }
}
