package com.xiaorui.socket.hander;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.vo.RequestVO;
import com.xiaorui.socket.base.network.customer.INetworkConsumer;
import com.xiaorui.socket.base.network.listener.INetworkEventListener;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author xp
 * @version 1.0.0
 */
@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Autowired
    private INetworkEventListener networkEventListener;
    @Autowired
    private INetworkConsumer consumer;

    /**
     * 读取消息
     * @param ctx
     * @param in
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in)
            throws Exception {
        int length = in.readInt();
        ByteBuf buf = in.readBytes(length);
        byte[] bodyByte = new byte[buf.readableBytes()];
        buf.readBytes(bodyByte);
        String body = new String(bodyByte);
        RequestVO requestVO = JSONObject.parseObject(body, RequestVO.class);
        consumer.consume(requestVO, ctx.channel());
    }

    /**
     * 当客户端连接服务器完成就会触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
        // 建立连接的时候创建session
        networkEventListener.onConnected(ctx);
    }


    /**
     * 发生异常
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        networkEventListener.onExceptionCaught(ctx, cause);
        ctx.close();
    }

    /**
     * 远程客户端关闭连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel [{}] is disconnected", ctx.channel().remoteAddress());
        // 关联连接关闭session
        networkEventListener.onDisconnected(ctx);;
        super.channelInactive(ctx);
    }


}
