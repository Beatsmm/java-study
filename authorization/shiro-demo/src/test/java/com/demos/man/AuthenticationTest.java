package com.demos.man;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AuthenticationTest {


    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @BeforeEach // 在方法开始前添加一个用户
    public void addUser(){
        simpleAccountRealm.addAccount("B brother","123456");
    }

    @Test
    public void testAuthentication(){
        // 构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        // 主体提交认证请求
        // 设置SecurityManager环境
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 获取当前主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("B brother", "123456");
        subject.login(token);

        // 用于判断用户是否认证成功
        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        subject.logout();
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        /**
         * 1、首先调用Subject.login(token)进行登陆，其会自动委托给Security Manager，调用前必须通过
         * SecurityUtils.setSecurityManage()设置,其实subject可以理解为当前用户
         * 2、SecurityManager负责真正的身份逻辑验证；它会委托给Authenticator进行身份验证
         * 3、Authenticator才是真正的身份验证者，Shiro API中核心的身份认证入口点，此处可以自定义插入自己的实现，
         * 4、Authenticator可能会委托给相应的AuthenticationStrategy进行Realm身份验证，默认
         * ModularRealmAuthenticator 会调用 AuthenticationStrategy 进行多 Realm 身份验证；
         * Authenticator 会把相应的 token 传入 Realm，从 Realm 获取身份验证信息，如果没有返回 / 抛出异常表示身份验证失败了。
         * 此处可以配置多个 Realm，将按照相应的顺序及策略进行访问。
         *
         * 总结：我们的代码通过Subject来进行认证和授权，而Subject又委托给SecurityManage，我们需要给Security注入Realm，
         * 从而让SecurityManager能得到合法的用户及其权限进行判断。
         */
    }



}
