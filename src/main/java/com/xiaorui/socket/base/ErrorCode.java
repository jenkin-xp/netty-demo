package com.xiaorui.socket.base;


public enum ErrorCode {

    ERROR_CODE_SUCCESS(0, "操作成功"),
    ERROR_CODE_ERROR_ACCOUNT(80900001, "用户账号或密码输入错误"),
    ;

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
