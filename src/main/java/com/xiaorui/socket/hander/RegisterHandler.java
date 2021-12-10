package com.xiaorui.socket.hander;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.vo.RequestVO;
import com.xiaorui.socket.base.vo.ResponseDTO;
import com.xiaorui.socket.base.vo.User;
import com.xiaorui.socket.base.concurrent.AbstractHandler;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.session.Session;
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
        RequestVO requestVO = (RequestVO) message;
        Session session = param;
        UserLoginDTO userLoginDTO = JSONObject.parseObject(requestVO.getBody(), UserLoginDTO.class);
        ResponseDTO<User> responseDTO = userService.register(userLoginDTO);
        responseDTO.setMessageId(requestVO.getMessageId());
        session.getChannel().writeAndFlush(responseDTO);
    }
}
