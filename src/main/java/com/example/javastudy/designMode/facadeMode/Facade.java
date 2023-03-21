package com.example.javastudy.designMode.facadeMode;

import java.math.BigDecimal;

public class Facade {


    public void getReport(){
        Order order = new Order();
        String orderNum = order.getOrderNum();
        Payment payment = new Payment(order);
        BigDecimal orderByAccount = payment.getOrderByAccount(orderNum);
        Delivery delivery = new Delivery();
        int deliveryTime = delivery.getDeliveryTime();
        System.out.println(String.format("报表：    订单号:%s,| 金额: %s元 ,| 配送时间:%s分钟",orderNum,orderByAccount,deliveryTime));

    }
}
