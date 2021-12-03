package com.xiaorui.socket.base.concurrent;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息字典接口〉
 *
 * @author xp
 * @date 2021-12-03 14:51
 * @version 1.0.0
 */
public interface IMessageDictionary {

  /**
   * 注册 id--handle
   * @param messageId
   * @param handler
   */
  void register(int messageId,  Class<? extends IHandler> handler);
  /**
   * 根据messageId获取handler
   * @param messageId
   * @return
   */
  IHandler findHandler(int messageId);
}