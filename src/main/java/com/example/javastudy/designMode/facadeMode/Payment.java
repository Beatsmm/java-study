package com.example.javastudy.designMode.facadeMode;

import java.math.BigDecimal;

public class Payment {

    private Order order;

    // 依赖注入
    public Payment(Order order) {
        this.order = order;
    }

    public BigDecimal getOrderByAccount(String orderNum){
        System.out.println(String.format("获取订单%s支付金额",orderNum));
        return BigDecimal.valueOf(500);
    }
}
