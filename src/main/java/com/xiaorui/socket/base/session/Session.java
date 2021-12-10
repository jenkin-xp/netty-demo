package com.xiaorui.socket.base.session;

import com.xiaorui.socket.base.vo.User;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Session {

    private static final Logger logger = LoggerFactory.getLogger(Session.class);

    /**
     * 与客户端的管道
     */
    private final Channel channel;
    /**
     * 用户信息
     * 用户信息可能为空。
     * 只有在register(登录)之后，里面才会赋值
     */
    private User user;
    /**
     * 创建时间
     */
    private final long createTime;

    private final HashMap<String, Object> hashMap;

    protected Session(Channel channel) {
        this.channel = channel;
        this.createTime = System.currentTimeMillis();
        hashMap = new HashMap<>();
    }

    /**
     * 玩session里面写入user，一般是在登录之后调用
     *
     * @param user 用户 信息
     */
    void registerUser(User user) {
        this.user = user;
    }

    /**
     * 关闭与客户端的连接
     */
    void close() {
        if (channel == null) {
            return;
        }
        try {
            if (channel.isActive() || channel.isOpen()) {
                channel.close().sync();
            }
        } catch (InterruptedException e) {
            logger.error("channel.close find error ", e);
        }

    }

    User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

    void put(String key, Object value) {
        hashMap.put(key, value);
    }

    Object getByKey(String key) {
        return hashMap.getOrDefault(key, null);
    }

}
