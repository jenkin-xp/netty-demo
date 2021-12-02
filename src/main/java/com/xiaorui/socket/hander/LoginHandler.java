package com.xiaorui.socket.hander;

import com.alibaba.fastjson.JSONObject;
import com.xiaorui.socket.base.ResponseDTO;
import com.xiaorui.socket.base.session.SessionManager;
import com.xiaorui.socket.base.User;
import com.xiaorui.socket.service.UserService;
import io.netty.channel.Channel;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoginHandler implements IHandler {

    private static final Logger log = LoggerFactory.getLogger(SimpleChannelInboundHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void processMessage(Channel channel, String msg) {
        JSONObject jsonObject = JSONObject.parseObject(msg);
        ResponseDTO<User> responseDTO = userService.login(jsonObject.getString("username"), jsonObject.getString("password"));
        if (responseDTO.getErrCode() == 0) {
            SessionManager.getInstance().register(channel, responseDTO.getData());
        }
        channel.writeAndFlush(responseDTO);
    }
}
