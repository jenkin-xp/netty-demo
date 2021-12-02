package com.xiaorui.socket.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = -943143895792475225L;
    private static final int SUCCESS = 0;
    private static final String SUCCESS_MSG = "操作成功！";
    private int errCode;
    private String message;
    private T data;
    private String traceId;

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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String toString() {
        return "ResponseDTO [errCode=" + this.errCode + ", message=" + this.message + ", data=" + this.data + "]";
    }
}
