package com.demos.token.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    // loginType是多账号认证的标识值

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = Arrays.asList("101","user.add","user.update","user.get","art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合---权限与角色可分开校验
     */

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = Arrays.asList("admin","super-admin");
        return list;
    }
}
