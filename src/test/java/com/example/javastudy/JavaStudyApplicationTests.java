package com.example.javastudy;

import com.example.javastudy.designMode.Response;
import com.example.javastudy.designMode.policyMode.Order;
import com.example.javastudy.designMode.policyMode.Payment;
import com.example.javastudy.designMode.policyMode.PaymentEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootTest
class JavaStudyApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Order order = new Order();
        order.setPayType("Wechat");
        order.setAmount(new BigDecimal("100"));
        Payment payment = applicationContext.getBean(order.getPayType(), Payment.class);
        Response pay = payment.pay(order);

        System.out.println(pay);

    }

}
