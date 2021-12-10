package com.xiaorui.socket.base.session;

import com.xiaorui.socket.base.vo.User;
import com.xiaorui.socket.base.message.IMessage;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/06 14:25
 * @Version V1.0
 **/
public class SessionManager {

    private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
    private static final SessionManager instance = new SessionManager();

    public static SessionManager getInstance() {
        return instance;
    }

    private SessionManager() {}

    // 已经登录的session
    private final ConcurrentMap<Integer, Session> uidSessionMap = new ConcurrentHashMap<>(16);

    public Session getSession(Integer userId) {
        return uidSessionMap.get(userId);
    }

    public Session[] getSessionArray() {
        Collection<Session> values = uidSessionMap.values();
        return values.toArray(new Session[values.size()]);
    }

    public ChannelGroup getChannelGroup() {
        ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        for (Session session : uidSessionMap.values()) {
            channelGroup.add(session.getChannel());
        }
        return channelGroup;
    }

    /**
     * 创建session
     * @param channel
     * @return
     */
    public Session create(Channel channel) {
        Session session = getSessionByChannel(channel);
        if (session == null) {
            session = new Session(channel);
            AttributeUtil.set(channel, SessionAttributeKey.SESSION, session);
            logger.info("session 创建成功");
        } else {
            logger.error("新连接建立时已存在Session，注意排查原因 " + channel.toString());
        }
        return session;
    }

    /**
     * 登录成功-->注册session
     *
     * @param channel channel
     * @param user    用户
     */
    public void register(Channel channel, User user) {
        Session session = getSessionByChannel(channel);
        session.registerUser(user);
        uidSessionMap.put(session.getUser().getId(), session);
    }

    public Session getSessionByChannel(Channel channel) {
        return AttributeUtil.get(channel, SessionAttributeKey.SESSION);
    }

    /**
     * 通过channel关闭通道
     *
     * @param channel
     */
    public void close(Channel channel) {
        Session session = getSessionByChannel(channel);
        if (session != null) {
            close(session);
        }
    }

    /**
     * 关闭session
     *
     * @param session
     */
    private void close(Session session) {
        unregister(session);
        session.close();
        logger.info("session close success");
    }

    private void unregister(Session session) {
        if (session != null) {
            AttributeUtil.set(session.getChannel(), SessionAttributeKey.SESSION, null);

            User user = session.getUser();
            if (user != null) {
                boolean remove = uidSessionMap.remove(user.getId(), session);
                logger.info("Session unregister, userId={}, remove={}", user.getId(), remove);
            }
        }
    }

    public void sendMessage(Session session, IMessage iMessage) {
        session.getChannel().writeAndFlush(iMessage);
    }

}
