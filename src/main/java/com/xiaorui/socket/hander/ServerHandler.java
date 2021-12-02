package com.xiaorui.socket.hander;

import com.xiaorui.socket.base.network.INetworkEventListener;
import com.xiaorui.socket.base.session.SessionManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author xp
 * @version 1.0.0
 */
@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Autowired
    private INetworkEventListener networkEventListener;

    /**
     * 读取消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        log.info("client msg:"+msg);
        String clientIdToLong= ctx.channel().id().asLongText();
        log.info("client long id:"+clientIdToLong);
        String clientIdToShort= ctx.channel().id().asShortText();
        log.info("client short id:"+clientIdToShort);
        if(msg.indexOf("bye")!=-1){
            //close
            ctx.channel().close();
        }else{
            //send to client
            ctx.channel().writeAndFlush("Yoru msg is:"+msg);
        }

    }

    /**
     * 当客户端连接服务器完成就会触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        // 建立连接的时候创建session
        networkEventListener.onConnected(ctx);
        ctx.channel().writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
        super.channelActive(ctx);
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
        log.info("\nChannel [{}] is disconnected", ctx.channel().remoteAddress());
        // 关联连接关闭session
        networkEventListener.onDisconnected(ctx);;
        super.channelInactive(ctx);
    }


}
