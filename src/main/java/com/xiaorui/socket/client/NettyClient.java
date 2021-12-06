package com.xiaorui.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是ServerBootstrap而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用NioSocketChannel作为客户端的通道实现
                    .handler(new ClientChannelInitializer());

            System.out.println("netty client start。。");
            //启动客户端去连接服务器端
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 8090).sync();
            //对通道关闭进行监听
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}