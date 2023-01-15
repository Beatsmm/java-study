package com.demos.man.controller;

import com.demos.man.entity.User;
import com.demos.man.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService userService;

    /**
     * 根据username从数据库中读取出用户信息
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view") // 权限管理
    public User findUserInfoByUsername(@RequestParam String username){
        return userService.findByUsername(username);
    }

    /**
     * 简单模拟从数据库添加用户信息成功
     */
    @PostMapping("/userAdd")
    @RequiresPermissions(("userInfo:add"))
    public String addUserInfo(){
        return "addUserInfo success!";
    }

    /**
     * 简单模拟从数据库删除用户成功
     */
    @DeleteMapping("/userDelete")
    @RequiresPermissions("userInfo:delete")
    public String deleteUserInfo() {
        return "deleteUserInfo success!";
    }
}
