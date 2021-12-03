package com.xiaorui.socket.base.message;

public interface IMessage {

    short getMessageId();

    void setMessageId(short messageId);

    short getStatusCode();

    void setStatusCode(short statusCode);

    int getLength();

    void setLength(int length);

    byte[] getBodyByte();

    void setBodyByte(byte[] body);

}
