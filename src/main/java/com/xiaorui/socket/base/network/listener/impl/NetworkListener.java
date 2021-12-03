package com.xiaorui.socket.base.network.listener.impl;

import com.xiaorui.socket.base.network.listener.INetworkEventListener;
import com.xiaorui.socket.base.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NetworkListener implements INetworkEventListener {

    private static final Logger log = LoggerFactory.getLogger(NetworkListener.class);

    @Override
    public void onConnected(ChannelHandlerContext ctx) {
        log.info("[{}]建立连接", ctx.channel().remoteAddress());
        SessionManager.getInstance().create(ctx.channel());
    }

    @Override
    public void onDisconnected(ChannelHandlerContext ctx) {
        log.info("[{}]断开连接", ctx.channel().remoteAddress());
        SessionManager.getInstance().close(ctx.channel());
    }

    @Override
    public void onExceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        log.error("发生异常-->[{}]", throwable.getMessage());
    }
}
