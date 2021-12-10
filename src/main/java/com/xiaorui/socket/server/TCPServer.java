package com.xiaorui.socket.server;

import com.xiaorui.socket.server.channel.websocket.WebSocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

/**
 * @author xp
 * @version 1.0.0
 */
@Component
@Slf4j
public class TCPServer {
    @Autowired
    private ServerBootstrap serverBootstrap;

    @Value("${tcp.port}")
    private int tcpPort;

    @Value("${boss.thread.count}")
    private int bossCount;

    @Value("${worker.thread.count}")
    private int workerCount;

    @Value("${so.backlog}")
    private int backlog;

    private ChannelFuture serverChannelFuture;

    @PostConstruct
    public void start() throws Exception {
        log.info("Starting server at {}", tcpPort);
        serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
        // serverChannelFuture.channel().closeFuture().sync();

//        EventLoopGroup bossGroup = new NioEventLoopGroup(bossCount); // (1)
//        EventLoopGroup workerGroup = new NioEventLoopGroup(workerCount);
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new WebSocketChannelInitializer())
//                    .option(ChannelOption.SO_BACKLOG, backlog);
//            ChannelFuture cf = b.bind(tcpPort).sync();
//            cf.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//
//            System.out.println("netty Server 关闭了");
//        }
    }

    @PreDestroy
    public void stop() throws Exception {
        serverChannelFuture.channel().closeFuture().sync();
    }

    public ServerBootstrap getServerBootstrap() {
        return serverBootstrap;
    }

    public void setServerBootstrap(ServerBootstrap serverBootstrap) {
        this.serverBootstrap = serverBootstrap;
    }

}
