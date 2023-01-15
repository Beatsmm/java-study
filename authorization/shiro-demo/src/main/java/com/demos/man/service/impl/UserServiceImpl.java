package com.demos.man.service.impl;

import com.demos.man.entity.User;
import com.demos.man.mapper.UserMapper;
import com.demos.man.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
