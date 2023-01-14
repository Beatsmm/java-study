package com.demos.man;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

public class MyRealmTest {


    @Test
    public void testAuthentication(){
        MyRealm myRealm = new MyRealm(); // 实现自己的realm实例

        // 1、构建SecurityManger环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myRealm);

        // 2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 获取主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("nanjinglizhi","123456");
        subject.login(token);

        // subject.isAuthenticated()方法返回一个boolean值，用于判断用户是否成功
        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        // 判断subject是否具有admin和user两个角色权限
        subject.checkRoles("admin","user");
        // 判断subject是否具有user:add权限
        subject.checkPermission("user:add");

    }
}
