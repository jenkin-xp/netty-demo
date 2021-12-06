package com.xiaorui.socket.server.channel.websocket;

import com.xiaorui.socket.base.message.codec.IMessageToWebSocketFrameEncoder;
import com.xiaorui.socket.base.message.codec.WebSocketFrameToIMessageDecoder;
import com.xiaorui.socket.base.util.SpringUtils;
import com.xiaorui.socket.base.util.SslUtil;
import com.xiaorui.socket.config.SslConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

/**
 * @Description webSocket的channel
 * @Author xp
 * @Date 2021/12/6 14:56
 * @Version V1.0
 **/
@Component
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChannelInitializer.class);

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
      SslConfig sslConfig = SslConfig.getInstance();
        if (sslConfig.isOpen()) {
            try {
                SSLContext sslContext = SslUtil
                        .createSSLContext(sslConfig.getType(), sslConfig.getPath(),
                                sslConfig.getPassword());
                // SSLEngine 此类允许使用ssl安全套接层协议进行安全通信
                SSLEngine engine = sslContext.createSSLEngine();
                // 服务器模式
                engine.setUseClientMode(false);
                //ssl双向认证
                engine.setNeedClientAuth(false);
                engine.setWantClientAuth(false);
                // engine.setEnabledProtocols(new String[] { "SSLv3", "TLSv1" })
                // TLSv1.2包括了SSLv3
                engine.setEnabledProtocols(new String[]{"TLSv1.2"});
                pipeline.addLast("ssl", new SslHandler(engine));

            } catch (SSLException e) {
                logger.debug("添加ssl出现错误", e);
            }
        }

        pipeline.addLast("http-codec", new HttpServerCodec()); // Http消息编码解码
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装
        pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持
        // 消息编解码
        pipeline.addLast("encoder", new IMessageToWebSocketFrameEncoder());
        pipeline.addLast("decoder", new WebSocketFrameToIMessageDecoder());

        WebSocketHandler webSocketHandler = SpringUtils.getObject(WebSocketHandler.class);
        pipeline.addLast(webSocketHandler);

    }

}
