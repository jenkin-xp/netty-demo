package com.xiaorui.socket.base.constant;

public class MessageValue {

    /**
     * 登录退出队列
     */
    public static final int QUEUE_LOGIN_LOGOUT = 1;

    /**
     * 业务队列
     */
    public static final int QUEUE_LOGIC = 2;

    public static final boolean SSL_OPEN = false;

    public static final String MESSAGE_TYPE_STRING = "STRING";
    public static final String MESSAGE_TYPE_BYTE = "BYTE";
    public static final int MESSAGE_CODEC_MAX_FRAME_LENGTH = 1024 * 1024;
    public static final int MESSAGE_CODEC_LENGTH_FIELD_LENGTH = 4;
    public static final int MESSAGE_CODEC_LENGTH_FIELD_OFFSET = 2;
    public static final int MESSAGE_CODEC_LENGTH_ADJUSTMENT = 0;
    public static final int MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP = 0;
}
