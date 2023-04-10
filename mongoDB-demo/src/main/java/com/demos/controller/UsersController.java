package com.demos.controller;


import com.demos.common.Page;
import com.demos.entity.Users;
import com.demos.entity.UsersQueryForm;
import com.demos.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UserService userService;

    public String result(int i){
        return i == 1 ? "success" : "false";
    }

    @PostMapping("/add")
    public String add(Users users){
        int i = userService.insertUser(users);
        return result(i);
    }

    @PostMapping("/update")
    public String update(Users users){
        int i = userService.updateUser(users);
        return result(i);
    }

    @GetMapping("/remove")
    public String remove(Long id) {
        int i = userService.removeUser(id);
        return result(i);
    }

    @PostMapping("/findOne")
    public Users findOne(Users users) {
        return userService.findById(users);

    }

    @PostMapping("/findLike")
    public List<Users> findLike(Users users) {
        return userService.findLike(users);

    }

    @PostMapping("/findMore")
    public List<Users> findMore(Users users) {
        return userService.findMore(users);

    }
    @PostMapping("/findTime")
    public List<Users> findTime(Users users) {
        return userService.findAge(users);

    }
    @PostMapping("/findByPage")
    public Page<Users> findByPage(@RequestBody UsersQueryForm queryForm) {
        return userService.findByPage(queryForm);

    }
}
