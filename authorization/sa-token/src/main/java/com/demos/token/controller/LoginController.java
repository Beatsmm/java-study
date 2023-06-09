package com.demos.token.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆测试
 */
@RestController
@RequestMapping("/test/")
public class LoginController {

    // 登陆 http://localhost:8081/test/doLogin?name=nanjinglizhi&pwd=123456
    @RequestMapping("doLogin")
    public SaResult doLogin(String name,String pwd){
        if ("nanjinglizhi".equals(name) && "123456".equals(pwd)){
            StpUtil.login(10001);
            return SaResult.ok("登陆成功");
        }
        return SaResult.error("登陆失败");
    }

    // 查询登陆状态 http://localhost:8081/test/isLoginhttp://localhost:8081/test/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin(){
        return SaResult.ok("是否登陆: " + StpUtil.isLogin());
    }

    // 查询token信息 http://localhost:8081/test/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo(){
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销 http://localhost:8081/test/logout
    @RequestMapping("logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok();
    }

    @RequestMapping("checkLogin")
    public SaResult checkLogin() {
        // 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.checkLogin();

        // 抛出异常后，代码将走入全局异常处理（GlobalException.java），如果没有抛出异常，则代表通过了登录校验，返回下面信息
        return SaResult.ok("校验登录成功，这行字符串是只有登录后才会返回的信息");
    }
}
