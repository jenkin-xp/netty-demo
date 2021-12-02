package com.xiaorui.socket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xiaorui.socket.mapper"})
public class NettyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyDemoApplication.class, args);
    }

}
