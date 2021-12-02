package com.xiaorui.socket.base.session;

import io.netty.util.AttributeKey;

public class SessionAttributeKey {

    /**
     * AttributeKey Session
     */
    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");
}
