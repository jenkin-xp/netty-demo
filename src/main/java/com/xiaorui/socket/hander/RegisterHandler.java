package com.xiaorui.socket.hander;

import com.xiaorui.socket.base.concurrent.AbstractHandler;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.message.impl.StringMessage;
import com.xiaorui.socket.base.session.Session;
import com.xiaorui.socket.service.UserService;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RegisterHandler extends AbstractHandler<IMessage, Session> {

    private static final Logger log = LoggerFactory.getLogger(SimpleChannelInboundHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void doAction() {
        StringMessage stringMessage = (StringMessage) message;
        Session session = param;
        session.getChannel().writeAndFlush("responseDTO");
    }
}
