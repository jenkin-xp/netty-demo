package com.xiaorui.socket.base.message.impl;

import com.xiaorui.socket.base.constant.MessageValue;
import com.xiaorui.socket.base.message.IMessage;

/**
 * 〈一句话功能简述〉<br>
 * 〈用于生成message〉
 *
 * @author xp
 * @date 2020-12-03
 * @version  1.0.0
 */
public class MessageFactory {

  /**
   * 返回一个与配置相关的message
   *
   * @return IMessage的子类
   */
  public static IMessage create(String messageType) {
    IMessage iMessage = null;
    switch (messageType) {
      case MessageValue.MESSAGE_TYPE_STRING:
        iMessage = new StringMessage();
        break;
      case MessageValue.MESSAGE_TYPE_BYTE:
        iMessage = new ByteMessage();
        break;
      default:
        break;
    }
    return iMessage;
  }

}
