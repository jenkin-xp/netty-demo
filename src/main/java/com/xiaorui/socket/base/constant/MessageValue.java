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
    // 解码时，处理每个帧数据的最大长度
    public static final int MESSAGE_CODEC_MAX_FRAME_LENGTH = 1024 * 1024;
    // 记录该帧数据长度的字段本身的长度
    public static final int MESSAGE_CODEC_LENGTH_FIELD_LENGTH = 4;
    // 该帧数据中，存放该帧数据的长度的数据的起始位置
    public static final int MESSAGE_CODEC_LENGTH_FIELD_OFFSET = 2;
    // 修改帧数据长度字段中定义的值，可以为负数
    public static final int MESSAGE_CODEC_LENGTH_ADJUSTMENT = 0;
    // 解析的时候需要跳过的字节数
    public static final int MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP = 0;
}
