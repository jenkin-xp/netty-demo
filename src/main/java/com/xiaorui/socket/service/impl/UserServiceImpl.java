package com.xiaorui.socket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaorui.socket.base.ErrorCode;
import com.xiaorui.socket.base.ResponseDTO;
import com.xiaorui.socket.base.User;
import com.xiaorui.socket.dto.user.UserLoginDTO;
import com.xiaorui.socket.entity.UserModel;
import com.xiaorui.socket.mapper.UserMapper;
import com.xiaorui.socket.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

    @Override
    public ResponseDTO<User> login(UserLoginDTO userLoginDTO) {
        UserModel userModel = queryOneByUsername(userLoginDTO.getUsername());
        if (userModel == null || !userModel.getPassword().equals(userLoginDTO.getPassword())) {
            return new ResponseDTO<>(ErrorCode.ERROR_CODE_ERROR_ACCOUNT);
        }
        User user = new User();
        user.setId(userModel.getId());
        user.setRoleId(Integer.valueOf(userModel.getRoleId()));
        return new ResponseDTO<>(user);
    }

    private UserModel queryOneByUsername(String username) {
        LambdaQueryWrapper<UserModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserModel::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public ResponseDTO<User> register(UserLoginDTO userLoginDTO) {
        UserModel userModel = queryOneByUsername(userLoginDTO.getUsername());
        if (userModel != null) {
            return new ResponseDTO<>(ErrorCode.ERROR_CODE_USER_EXIST);
        }
        userModel = new UserModel();
        userModel.setUsername(userLoginDTO.getUsername());
        userModel.setPassword(userLoginDTO.getPassword());
        this.save(userModel);
        return new ResponseDTO<>();
    }
}
