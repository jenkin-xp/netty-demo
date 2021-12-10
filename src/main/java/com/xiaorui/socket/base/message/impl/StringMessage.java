package com.xiaorui.socket.base.message.impl;

import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;

public class StringMessage extends BaseMessage {

    /**
     * 发送内容
     */
    private String body = "";

    private String errMsg;

    public StringMessage() {
    }

    public StringMessage(Object data) {
        this.body = JSONObject.toJSONString(data);
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.statusCode = FAIL_CODE;
        this.errMsg = errMsg;
    }

    public StringMessage(short messageId) {
        this.messageId = messageId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBody(Object body) {
        this.body = JSONObject.toJSONString(body);
    }

    @Override
    public byte[] getBodyByte() {
        return body.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void setBodyByte(byte[] body) {
        this.body = new String(body, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "StringMessage{" +
                "messageId=" + messageId +
                ", statusCode=" + statusCode +
                ", length=" + length +
                ", body='" + body + '\'' +
                '}';
    }
}
