package com.xiaorui.socket.base.vo;

import lombok.Data;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/10 14:17
 * @Version V1.0
 **/
@Data
public class RequestVO {

    private String body;

    private short messageId;

    public RequestVO(String body, short messageId) {
        this.body = body;
        this.messageId = messageId;
    }
}
