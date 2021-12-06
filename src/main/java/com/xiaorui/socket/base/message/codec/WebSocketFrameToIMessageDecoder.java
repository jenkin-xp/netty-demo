package com.xiaorui.socket.base.message.codec;

import com.xiaorui.socket.base.constant.MessageValue;
import com.xiaorui.socket.base.message.IMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @Description WebSocketFrame转换成IMessage
 * @Author xp
 * @Date 2021/12/6 14:51
 * @Version V1.0
 **/
public class WebSocketFrameToIMessageDecoder extends MessageToMessageDecoder<Object> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        if (msg instanceof FullHttpRequest) {
          // 传统的HTTP接入
            out.add(msg);
        } else if (msg instanceof WebSocketFrame) {
            // out.add(msg)
            // WebSocket接入
            ByteBuf content = ((WebSocketFrame) msg).content();
            MessageDecoder messageDecoder = new MessageDecoder(MessageValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
                    MessageValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, MessageValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
                    MessageValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, MessageValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP, false,
                    MessageValue.MESSAGE_TYPE_BYTE);
            IMessage iMessage = messageDecoder.decodePub(ctx, content);
            out.add(iMessage);

        } else {
            out.add(msg);
        }
    }
}
