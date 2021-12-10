package com.xiaorui.socket.server.channel.websocket;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.vo.RequestVO;
import com.xiaorui.socket.base.constant.MessageValue;
import com.xiaorui.socket.base.exception.MessageCodecException;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.message.codec.MessageDecoder;
import com.xiaorui.socket.base.network.customer.INetworkConsumer;
import com.xiaorui.socket.base.network.listener.INetworkEventListener;
import com.xiaorui.socket.base.util.HttpResponseUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/6 14:25
 * @Version V1.0
 **/
@Component
@Slf4j
@Scope("prototype")
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {
    @Autowired
    private INetworkEventListener listener;
    @Autowired
    private INetworkConsumer consumer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws MessageCodecException {
        if (msg instanceof FullHttpRequest) {
          // 传统的HTTP接入
          handleHttpMessage(ctx, msg);
        } else if (msg instanceof WebSocketFrame) {
          // WebSocket接入
          handleWebSocketMessage(ctx, msg);
        } else if (msg instanceof IMessage) {
          // 这里已经通过WebSocketFrameToIMessageDecoder进行解码，获得我们设置好的IMessage类了
            log.info("收到消息：[{}]", msg);
            byte[] bodyByte = ((IMessage) msg).getBodyByte();
            String body = new String(bodyByte);
            RequestVO requestVO = JSONObject.parseObject(body, RequestVO.class);
            consumer.consume(requestVO, ctx.channel());
        }
    }

    /**
     * 处理WebSocket中的Http消息
     *
     * @param ctx 上下文
     * @param msg 消息
     */
    private void handleHttpMessage(ChannelHandlerContext ctx, Object msg) {
        // 传统的HTTP接入
        FullHttpRequest request = (FullHttpRequest) msg;

        // 如果HTTP解码失败，返回HHTP异常
        if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
            HttpResponseUtils.sendHttpResponse(ctx, request,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        // 正常WebSocket的Http连接请求，构造握手响应返回
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://" + request.headers().get(HttpHeaderNames.HOST), null, false);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) { // 无法处理的websocket版本
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else { // 向客户端发送websocket握手,完成握手
            handshaker.handshake(ctx.channel(), request);
        }
    }

    /**
     * 处理WebSocket中的WebSocket消息
     *
     * @param ctx 上下文
     * @param msg 消息
     */
    private void handleWebSocketMessage(ChannelHandlerContext ctx, Object msg) throws MessageCodecException {
        ByteBuf content = ((WebSocketFrame) msg).content();
        MessageDecoder messageDecoder = new MessageDecoder(MessageValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
                MessageValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, MessageValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
                MessageValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, MessageValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP, false,
                MessageValue.MESSAGE_TYPE_BYTE);
        IMessage iMessage = messageDecoder.decodePub(ctx, content);
        // WebSocket接入
        byte[] bodyByte = iMessage.getBodyByte();
        String body = new String(bodyByte);
        RequestVO requestVO = JSONObject.parseObject(body, RequestVO.class);
        consumer.consume(requestVO, ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        listener.onConnected(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        listener.onDisconnected(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        listener.onExceptionCaught(ctx, throwable);
    }

}
