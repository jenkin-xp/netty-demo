package com.xiaorui.socket.hander;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.ResponseDTO;
import com.xiaorui.socket.base.User;
import com.xiaorui.socket.base.concurrent.AbstractHandler;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.message.impl.StringMessage;
import com.xiaorui.socket.base.session.Session;
import com.xiaorui.socket.base.session.SessionManager;
import com.xiaorui.socket.dto.user.UserLoginDTO;
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
        String body = stringMessage.getBody();
        UserLoginDTO userLoginDTO = JSONObject.parseObject(body, UserLoginDTO.class);
        ResponseDTO<User> responseDTO = userService.register(userLoginDTO);
        StringMessage resultMessage = new StringMessage();
        if (responseDTO.getErrCode() > 0) {
            resultMessage.setErrMsg(resultMessage.getErrMsg());
        } else {
            resultMessage.setBody(responseDTO.getData());
        }
        SessionManager.getInstance().sendMessage(session, resultMessage);
    }
}
