package com.demos.token.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kick/")
public class KickoutController {


    // 将指定账号强制注销
    // http://localhost:8081/test/logout
    @RequestMapping("logout")
    public SaResult logout(long userId){
        System.out.println("----");
        // 强制注销等于对方主动调用了注销方法，再次访问会提示：token无效
        StpUtil.logout(userId);

        // 返回
        return SaResult.ok();
    }

    // 将指定账号踢下线
    @RequestMapping("kickout")
    public SaResult kickout(long userId){
        // 踢人下线不会清楚token信息，而是将其打上特定标记，下次访问会提示：Token被踢下线
        StpUtil.kickout(userId);
        return SaResult.ok();
    }

    /**
     * 在强制注销和踢人下线后，再次访问一下登陆校验接口对比二者返回信息的不同
     *  http://localhost:8081/test/checkLogin
     */

    // 根据token踢人
    @RequestMapping("kickoutByTokenValue")
    public SaResult kickoutByTokenValue(String token){
        StpUtil.kickoutByTokenValue(token);
        return SaResult.ok();
    }
}
