package com.xiaorui.socket.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    private Integer id;

    /**
     * 获取用户状态id
     *
     * @return 状态id
     */
    private Integer status;

    private Integer roleId;

    /**
     * 获取用户角色类型id
     *
     * @return 角色类型id
     */
    public Integer getRole() {
        return roleId;
    }

}
