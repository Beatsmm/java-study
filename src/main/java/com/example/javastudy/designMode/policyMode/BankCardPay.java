package com.example.javastudy.designMode.policyMode;

import com.example.javastudy.designMode.Response;
import org.springframework.stereotype.Component;

@Component("Bank_card")
public class BankCardPay implements Payment{


    @Override
    public Response pay(Order order) {
        if (true){
            return Response.ok("test");
        }else {
            return Response.error();
        }
    }
}
