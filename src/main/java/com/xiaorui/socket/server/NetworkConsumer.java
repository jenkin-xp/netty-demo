package com.xiaorui.socket.server;

import com.xiaorui.socket.base.concurrent.IHandler;
import com.xiaorui.socket.base.concurrent.IMessageDictionary;
import com.xiaorui.socket.base.constant.MessageValue;
import com.xiaorui.socket.base.message.IMessage;
import com.xiaorui.socket.base.network.customer.INetworkConsumer;
import com.xiaorui.socket.base.network.processor.IProcessor;
import com.xiaorui.socket.base.session.Session;
import com.xiaorui.socket.base.session.SessionManager;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class NetworkConsumer implements INetworkConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NetworkConsumer.class);

    private Map<Integer, IProcessor> processors = new HashMap<>(10);

    @Autowired
    private IMessageDictionary messageDictionary;

    @PostConstruct
    public void init() {
        registerProcessor(MessageValue.QUEUE_LOGIC, new LogicProcessor());
    }

    private void registerProcessor(int queueId, IProcessor processor) {
        processors.put(queueId, processor);
    }

    @Override
    public void consume(IMessage message, Channel channel) {
        Session session = SessionManager.getInstance().getSessionByChannel(channel);
        if (session == null) {
            logger.debug("consume session is not found");
            return;
        }
        logger.info("messageId: [{}]", message.getMessageId());
        IHandler handler = messageDictionary.findHandler(message.getMessageId());
        handler.setMessage(message);
        handler.setParam(session);
        IProcessor processor = processors.get(MessageValue.QUEUE_LOGIC);
        processor.process(handler);
    }
}
