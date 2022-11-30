package com.example.javastudy.designMode.policyMode;

import com.example.javastudy.designMode.Response;
import org.springframework.stereotype.Component;

@Component("ALiPay")
public class AliPay implements Payment{

    @Override
    public Response pay(Order order) {
        if (true){
            return Response.ok("test");
        }else {
            return Response.error();
        }
    }
}
