package com.example.javastudy.designMode.dip;

public class WXPay implements Payment{
    @Override
    public void Pay() {
        System.out.println("微信支付");
    }
}
