package com.xiaorui.socket.base.exception;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/3 18:09
 * @Version V1.0
 **/
public class MessageCodecException extends Exception{

    public MessageCodecException(String errMsg) {
        super(errMsg);
    }

    public MessageCodecException(Throwable cause) {
        super(cause);
    }

}
