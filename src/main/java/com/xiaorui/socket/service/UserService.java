package com.xiaorui.socket.service;

import com.xiaorui.socket.base.ResponseDTO;
import com.xiaorui.socket.base.User;

public interface UserService {

    ResponseDTO<User> login(String username, String password);

}
