package com.demos.token.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MySaTokenListener implements SaTokenListener {

    // 登录
    @Override
    public void doLogin(String s, Object o, String s1, SaLoginModel saLoginModel) {
      log.info("自定义监听器实现----doLogin");
    }

    // 注销
    @Override
    public void doLogout(String s, Object o, String s1) {
        log.info("自定义监听器实现----doLogout");
    }

    // 被踢下线触发
    @Override
    public void doKickout(String s, Object o, String s1) {
        log.info("自定义监听器实现----doKickout");
    }

    // 被顶下线触发
    @Override
    public void doReplaced(String s, Object o, String s1) {
        log.info("自定义监听器实现----doReplaced");
    }

    // 被封时候触发
    @Override
    public void doDisable(String s, Object o, String s1, int i, long l) {
        log.info("自定义监听器实现----doDisable");
    }

    // 被解封时候触发
    @Override
    public void doUntieDisable(String s, Object o, String s1) {
        log.info("自定义监听器实现----doUntieDisable");
    }

    // 二级认证时触发
    @Override
    public void doOpenSafe(String s, String s1, String s2, long l) {
        log.info("自定义监听器实现----doOpenSafe");
    }

    // 每次关闭二级认证时触发
    @Override
    public void doCloseSafe(String s, String s1, String s2) {
        log.info("自定义监听器实现----doCloseSafe");
    }

    // 每次创建session时候触发
    @Override
    public void doCreateSession(String s) {
        log.info("自定义监听器实现----doCreateSession");
    }

    // 每次注销seesion时触发
    @Override
    public void doLogoutSession(String s) {
        log.info("自定义监听器实现----doLogoutSession");
    }

    // 每次token续期时触发
    @Override
    public void doRenewTimeout(String s, Object o, long l) {
        log.info("自定义监听器实现----doRenewTimeout");
    }
}
