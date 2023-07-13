package com.example.javastudy.skill.check;


import com.example.javastudy.designMode.Response;
import com.example.javastudy.skill.stream.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/test/check")
@RestController
public class TestVaildController {


    @PostMapping("/addUser")
    public Response addUser(@RequestBody @Valid User user){
        return Response.ok(user);
    }
}
