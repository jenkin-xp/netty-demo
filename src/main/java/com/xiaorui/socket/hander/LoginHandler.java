package com.xiaorui.socket.hander;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.ResponseDTO;
import com.xiaorui.socket.base.concurrent.AbstractHandler;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.message.impl.StringMessage;
import com.xiaorui.socket.base.session.Session;
import com.xiaorui.socket.base.session.SessionManager;
import com.xiaorui.socket.base.User;
import com.xiaorui.socket.service.UserService;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoginHandler extends AbstractHandler<IMessage, Session> {

    private static final Logger log = LoggerFactory.getLogger(SimpleChannelInboundHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void doAction() {
        StringMessage stringMessage = (StringMessage) message;
        String body = stringMessage.getBody();
        body = body.substring(0, stringMessage.getLength());
        JSONObject jsonObject = JSONObject.parseObject(body);
        ResponseDTO<User> responseDTO = userService.login(jsonObject.getString("username"), jsonObject.getString("password"));
        Session session = param;
        if (responseDTO.getErrCode() == 0) {
            SessionManager.getInstance().register(session.getChannel(), responseDTO.getData());
        }
        session.getChannel().writeAndFlush(responseDTO);
    }
}
