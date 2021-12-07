package com.xiaorui.socket.task;

import com.xiaorui.socket.base.session.Session;
import com.xiaorui.socket.base.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/6 15:32
 * @Version V1.0
 **/
@Component
@Slf4j
public class UserTask {

    private int times;

    @Scheduled(fixedRate = 60000)
    public void printLoginUser() {
        Session[] sessionArray = SessionManager.getInstance().getSessionArray();
        log.info("task[{}], 当前登录用户：[{}]", ++ times, sessionArray.length);
    }

}
