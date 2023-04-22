package com.example.javastudy.safe;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testPrevent/")
public class TestController {


    @RequestMapping("/test")
    public Response test(){
        return Response.success("调用成功");
    }

    @RequestMapping("/test2")
    @Prevent(message = "10s内请勿重复提交", value = "10")
    public Response test2(){
        return Response.success("调用成功");
    }
}
