package com.xiaorui.socket.base.message.impl;

import com.xiaorui.socket.base.util.ByteUtils;

import java.util.Arrays;

public class ByteMessage extends BaseMessage {

    /**
     * 具体内容
     */
    private byte[] body = new byte[0];

    public void addAttr(int result) {
        byte[] tmpBytes = ByteUtils.intToBytes4(result);
        body =  ByteUtils.concat(body, tmpBytes);
    }

    public void addAttr(short result) {
        byte[] tmpBytes = ByteUtils.shortToByte(result);
        ByteUtils.concat(body, tmpBytes);
    }

    public void addAttr(float result) {
        byte[] tmpBytes = ByteUtils.float2byte(result);
        ByteUtils.concat(body, tmpBytes);
    }

    public void addAttr(long result) {
        byte[] tmpBytes = ByteUtils.longToBytes(result);
        ByteUtils.concat(body, tmpBytes);
    }

    public void addAttr(String result) {
        byte[] tmpBytes = ByteUtils.stringToBytes(result);
        addAttr(tmpBytes.length);
        ByteUtils.concat(body, tmpBytes);
    }

    @Override
    public byte[] getBodyByte() {
        return body;
    }

    @Override
    public void setBodyByte(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ByteMessage{" + " messageId=" + messageId + ", statusCode=" + statusCode + ", length=" + length + ",body="
                + Arrays.toString(body) + '}';
    }

}
