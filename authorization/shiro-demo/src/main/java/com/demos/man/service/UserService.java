package com.demos.man.service;

import com.demos.man.entity.User;

public interface UserService {

    User findByUsername(String username);
}
