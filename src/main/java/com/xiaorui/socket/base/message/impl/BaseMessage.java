package com.xiaorui.socket.base.message.impl;

import com.xiaorui.socket.base.message.IMessage;

public abstract class BaseMessage implements IMessage {

    /**
     * 消息来源的flag
     */
    protected short flag;
    /**
     * 消息号
     */
    protected short messageId;
    /**
     * 状态码
     */
    protected short statusCode = 0;
    /**
     * 内容长度
     */
    protected int length;

    protected short SUCCESS_CODE = 0;

    protected short FAIL_CODE = 1;

    @Override
    public short getMessageId() {
        return messageId;
    }

    @Override
    public void setMessageId(short messageId) {
        this.messageId = messageId;
    }

    @Override
    public short getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(short statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }
}
