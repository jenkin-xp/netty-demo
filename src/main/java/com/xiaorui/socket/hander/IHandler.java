package com.xiaorui.socket.hander;


import io.netty.channel.Channel;

public interface IHandler {

    void processMessage(Channel channel, String msg);

}
