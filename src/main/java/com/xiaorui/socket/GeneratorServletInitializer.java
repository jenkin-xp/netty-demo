package com.xiaorui.socket;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Description Web程序启动类
 * @Author xp
 * @Date 2021/12/7 14:03
 * @Version V1.0
 **/
public class GeneratorServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(NettyDemoApplication.class);
    }

}
