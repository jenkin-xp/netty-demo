package com.xiaorui.socket.server.biz;

import com.xiaorui.socket.base.concurrent.IHandler;
import com.xiaorui.socket.base.concurrent.IMessageDictionary;
import com.xiaorui.socket.base.enums.MessageIdEnum;
import com.xiaorui.socket.base.util.SpringUtils;
import com.xiaorui.socket.hander.EnterRoomHandler;
import com.xiaorui.socket.hander.LeaveRoomHandler;
import com.xiaorui.socket.hander.LoginHandler;
import com.xiaorui.socket.hander.RegisterHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息字典绑定〉
 *
 * @author xp
 * @date 2021-12-03 14:51
 * @version 1.0.0
 */
@Component
public class MessageHandlerDictionary implements IMessageDictionary {
  private final Map<Integer, Class<? extends IHandler>> idHandleMap = new HashMap<>(10);

  @PostConstruct
  public void init() {
    register(MessageIdEnum.REGISTER.getId(), RegisterHandler.class);
    register(MessageIdEnum.LOGIN.getId(), LoginHandler.class);
    register(MessageIdEnum.ENTER_ROOM.getId(), EnterRoomHandler.class);
    register(MessageIdEnum.LEAVE_ROOM.getId(), LeaveRoomHandler.class);
  }

  @Override
  public void register(int messageId, Class<? extends IHandler> handler) {
    idHandleMap.put(messageId, handler);
  }

  @Override
  public IHandler findHandler(int messageId) {
    Class<? extends IHandler> clazz = idHandleMap.get(messageId);
    if (clazz != null) {
      try {
        return SpringUtils.getObject(clazz);
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }
}