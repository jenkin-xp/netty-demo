package com.xiaorui.socket.client;

import com.xiaorui.socket.base.enums.MessageIdEnum;
import com.xiaorui.socket.base.message.impl.ByteMessage;
import com.xiaorui.socket.base.message.impl.StringMessage;
import com.xiaorui.socket.dto.user.UserLoginDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(NettyClientHandler.class);
    /**
     * 当客户端连接服务器完成就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // ByteBuf buf = Unpooled.copiedBuffer("HelloServer".getBytes(CharsetUtil.UTF_8));
        StringMessage stringMessage = new StringMessage();
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("lisi");
        userLoginDTO.setPassword("lisi123456");
        stringMessage.setBody(userLoginDTO);
        stringMessage.setMessageId((short) MessageIdEnum.LOGIN.getId());
        ctx.writeAndFlush(stringMessage);
    }

    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteMessage byteMessage = (ByteMessage) msg;
        log.info("服务端的消息状态: [{}]", byteMessage.getStatusCode() == 0 ? "成功" : "失败");
        log.info("收到服务端的消息: [{}]", new String(byteMessage.getBodyByte()));
        log.info("服务端的地址: [{}]", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}