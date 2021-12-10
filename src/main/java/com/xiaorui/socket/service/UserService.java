package com.xiaorui.socket.service;

import com.xiaorui.socket.base.vo.ResponseDTO;
import com.xiaorui.socket.base.vo.User;
import com.xiaorui.socket.dto.user.UserLoginDTO;

public interface UserService {

    ResponseDTO<User> login(UserLoginDTO userLoginDTO);

    ResponseDTO<User> register(UserLoginDTO userLoginDTO);

}
