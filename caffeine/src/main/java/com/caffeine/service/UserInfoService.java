package com.caffeine.service;

import com.caffeine.dto.UserInfo;

public interface UserInfoService {

    // 增加用户信息
    void addUserInfo(UserInfo userInfo);

    // 获取用户信息
    UserInfo getByName(Integer id);

    // 修改用户信息
    UserInfo update(UserInfo userInfo);

    // 删除用户信息
    void deleteById(Integer id);

}
