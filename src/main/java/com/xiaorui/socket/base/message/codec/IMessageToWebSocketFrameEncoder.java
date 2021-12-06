package com.xiaorui.socket.base.message.codec;

import com.xiaorui.socket.base.message.IMessage;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;

/**
 * @Description WebSocketFrameEncoder编码器
 * @Author xp
 * @Date 2021/12/6 14:51
 * @Version V1.0
 **/
public class IMessageToWebSocketFrameEncoder extends MessageToMessageEncoder<IMessage> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, IMessage iMessage, List<Object> list)
          throws Exception {
    //组合缓冲区
    CompositeByteBuf byteBuf = Unpooled.compositeBuffer();

    byte[] bodyBytes = iMessage.getBodyByte();
    byteBuf.writeShort(iMessage.getMessageId());
    byteBuf.writeShort(iMessage.getStatusCode());
    byteBuf.writeInt(bodyBytes.length);
    byteBuf.writeBytes(bodyBytes);

    list.add(new BinaryWebSocketFrame(byteBuf));
  }
}
