package com.demos.token.controller;


import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/at-check/")
public class AtCheckController {

    /**
     * 1、调用登陆接口
     *http://localhost:8081/test/doLogin?name=nanjinglizhi&pwd=123456
     * 2、在配置类中注册拦截器 SaInterceptor
     */

    // http://localhost:8081/at-check/checkLogin
    @SaCheckLogin
    @RequestMapping("checkLogin")
    public SaResult checkLogin(){
        // 通过注解鉴权后才能进入
        return SaResult.ok();
    }

    // 权限校验 http://localhost:8081/at-check/checkPermission
    @SaCheckPermission("user.add")
    @RequestMapping("/checkPermission")
    public SaResult checkPermission(){
        return SaResult.ok();
    }

    // 权限校验 一次性校验多个权限，必须全部拥有才可以进入方法
    // http://localhost:8081/at-check/checkPermission2
    @SaCheckPermission(value = {"user.add","user.delete","user.update"},mode = SaMode.AND)
    @RequestMapping("/checkPermission2")
    public SaResult checkPermission2(){
        return SaResult.ok();
    }


    // 权限校验 一次性校验多个权限，or的关系，满足一个就能进入
    // http://localhost:8081/at-check/checkPermission3
    @SaCheckPermission(value = {"user.add","user.delete","user.update"},mode = SaMode.OR)
    @RequestMapping("/checkPermission3")
    public SaResult checkPermission3(){
        return SaResult.ok();
    }

    // 角色校验 只有super-admin角色账户才可以进入方法
    // http://localhost:8081/at-check/checkRole
    @SaCheckRole("super-admin")
    @RequestMapping("checkRole")
    public SaResult checkRole(){
        return SaResult.ok();
    }

    // 角色权限双重"or校验",具有user.add权限或者"admin"角色即可通过校验
    // http://localhost:8081/at-check/userAdd
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add",orRole = "admin")
    public SaResult userAdd(){
        return SaResult.data("用户信息");
    }

    // 忽略校验 http://localhost:8081/at-check/ignore
    // 使用@SaIgnore修饰的方法，无需任何校验即可进入，
    @SaIgnore
    @SaCheckLogin
    @RequestMapping("ignore")
    public SaResult ignore(){
        return SaResult.ok();
    }

}
