package com.example.javastudy.skill.sign;

import com.example.javastudy.designMode.Response;
import com.example.javastudy.skill.optional.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Signature
    @PostMapping("/{id}")
    public Response<String> myController(@PathVariable String id
            , @RequestParam String client
            , @RequestBody User user) {
        return Response.ok(String.join(",", id, client, user.toString()));
    }



}
