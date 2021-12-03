package com.xiaorui.socket.base.enums;

/**
 * 消息ID
 */
public enum MessageIdEnum {

    REGISTER(0, "注册"),
    LOGIN(1, "登录"),
    ENTER_ROOM(2, "进入房间"),
    LEAVE_ROOM(3, "离开房间"),
    ;

    private int id;

    private String name;

    MessageIdEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
