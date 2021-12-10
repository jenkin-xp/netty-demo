package com.xiaorui.socket.base.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiaorui.socket.base.exception.ErrorCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = -943143895792475225L;
    private static final int SUCCESS = 0;
    private static final String SUCCESS_MSG = "操作成功！";
    private int errCode;
    private String message;
    private short messageId;
    private T data;

    public ResponseDTO() {
        this.errCode = SUCCESS;
        this.message = SUCCESS_MSG;
    }

    public ResponseDTO(ErrorCode errorCode) {
        this.errCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ResponseDTO(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public ResponseDTO(int errCode, String message, T data) {
        this.errCode = errCode;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(T data) {
        this.errCode = SUCCESS;
        this.message = SUCCESS_MSG;
        this.data = data;
    }

    public short getMessageId() {
        return messageId;
    }

    public void setMessageId(short messageId) {
        this.messageId = messageId;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "ResponseDTO [errCode=" + this.errCode + ", message=" + this.message + ", data=" + this.data + "]";
    }
}
