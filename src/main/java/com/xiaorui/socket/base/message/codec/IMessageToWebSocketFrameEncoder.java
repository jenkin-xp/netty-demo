package com.xiaorui.socket.base.message.codec;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.vo.ResponseDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * @Description WebSocketFrameEncoder编码器
 * @Author xp
 * @Date 2021/12/6 14:51
 * @Version V1.0
 **/
public class IMessageToWebSocketFrameEncoder extends MessageToMessageEncoder<ResponseDTO<?>> {

//  @Override
//  protected void encode(ChannelHandlerContext channelHandlerContext, IMessage iMessage, List<Object> list)
//          throws Exception {
//    //组合缓冲区
////    CompositeByteBuf byteBuf = Unpooled.compositeBuffer();
//
////    StringMessage stringMessage = (StringMessage) iMessage;
////    byteBuf.writeShort(stringMessage.getMessageId());
////    byteBuf.writeShort(stringMessage.getStatusCode());
////    if (stringMessage.getBody() != null) {
////      byte[] bodyBytes = stringMessage.getBodyByte();
////      byteBuf.writeInt(bodyBytes.length);
////      byteBuf.writeBytes(bodyBytes);
////    }
//    list.add(new TextWebSocketFrame(JSONObject.toJSONString(iMessage)));
//  }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseDTO<?> responseDTO, List<Object> list)
            throws Exception {
        list.add(new TextWebSocketFrame(JSONObject.toJSONString(responseDTO)));
    }
}
