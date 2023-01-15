package com.demos.man.mapper;

import com.demos.man.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
