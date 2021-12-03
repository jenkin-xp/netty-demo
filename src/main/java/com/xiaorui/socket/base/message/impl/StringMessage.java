package com.xiaorui.socket.base.message.impl;

import java.nio.charset.StandardCharsets;

public class StringMessage extends BaseMessage {

    /**
     * 发送内容
     */
    private String body;

    public StringMessage() {
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
