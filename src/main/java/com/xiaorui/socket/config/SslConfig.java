package com.xiaorui.socket.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/6 14:42
 * @Version V1.0
 **/
public class SslConfig {

    private final static SslConfig sslConfig = new SslConfig();

    @Value("${ssl.open-status}")
    private boolean isOpen;

    @Value("${ssl.type}")
    private String type;

    @Value("${ssl.path}")
    private String path;

    @Value("${ssl.password}")
    private String password;

    public static SslConfig getInstance() {
        return sslConfig;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
