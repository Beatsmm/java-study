package com.example.javastudy.designMode.dip;

public class AliPay implements Payment{

    @Override
    public void Pay() {
        System.out.println("支付宝支付");
    }
}
