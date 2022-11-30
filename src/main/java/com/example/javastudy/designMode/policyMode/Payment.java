package com.example.javastudy.designMode.policyMode;


import com.example.javastudy.designMode.Response;

/**
 * 第三方支付方式
 */
public interface Payment {


    /**
     * 订单信息
     */
    Response pay(Order order);
}
