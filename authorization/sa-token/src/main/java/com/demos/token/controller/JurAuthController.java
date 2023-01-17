package com.demos.token.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jur")
public class JurAuthController {


    /**
     * 1、首先调用登陆接口进行登陆
     * http://localhost:8081/test/doLogin?name=nanjinglizhi&pwd=123456
     * 2、实现StpInterface接口，Sa-token将从此实现累获取每个账号拥有的权限
     */


    // 查询权限
    // http://localhost:8081/jur/getPermission
    @RequestMapping("/getPermission")
    public SaResult getPermission(){
        // 查询权限信息，如果当前会话未登陆，返回一个空的集合
        List<String> permissionList = StpUtil.getPermissionList();
        System.out.println("当前登录账号拥有的所有权限：" + permissionList);

        // 查询角色信息，如果当前会话未登陆，返回一个空的集合
        List<String> roleList = StpUtil.getRoleList();
        System.out.println("当前登录账号拥有的所有角色：" + roleList);

        return SaResult.ok().set("roleList",roleList).set("permissionList",permissionList);

    }

    // 权限校验
    //
    @RequestMapping("checkPermission")
    public SaResult checkPermission(){
        // 判断账号是否拥有一个权限，返回true或者false
        // 没有登陆永远返回false
        StpUtil.hasPermission("user.add");
        // 指定多个，全部有才能返回true
        StpUtil.hasPermissionAnd("user.add","user.delete","user.get");
        // 指定多个，又一个就能返回true
        StpUtil.hasPermissionOr("user.add","user.delete","user.get");

        // 校验:当前账号是否拥有一个权限，校验不通过时会抛出 NotRoleException 异常(如果没通过永远校验失败)
        StpUtil.checkPermission("user.add");
        // 指定多个必须全部拥有才会校验通过
        StpUtil.checkPermissionAnd("user.add","user.delete","user.get");
        // 指定多个，只要拥有一个就会校验通过
        StpUtil.checkPermissionOr("user.add","user.delete","user.get");
        return SaResult.ok();
    }

    // 权限通配符 ----- http://localhost:8081/jur/wildcardPermission
    @RequestMapping("/wildcardPermission")
    public SaResult wildcardPermission(){
        // 前提条件: 在StpInterface实现类中，为账号返回了"art.*" 泛权限
        StpUtil.hasPermission("art.add"); // true
        StpUtil.hasPermission("art.delete"); // true
        StpUtil.hasPermission("goods.add"); // false

        // 符合可以出现在任意位置，比如权限码的开头，当账号拥有"*.delete"时
        StpUtil.hasPermission("goods.add"); // false
        StpUtil.hasPermission("goods.delete"); // true
        StpUtil.hasPermission("art.delete"); // true

        // 也可以出现在权限码的中间，比如当账号拥有"shop.*.user"时
        StpUtil.hasPermission("shop.add.user"); // true
        StpUtil.hasPermission("shop.delete.user"); // true
        StpUtil.hasPermission("shop.delete.goods"); // false

        // 注意
        // 1、上帝权限：当一个账号拥有"*"权限时，他可以验证通过任何权限码
        // 2、角色校验也可以加*，指定泛角色，例如："*admin"
        return SaResult.ok();
    }
}
