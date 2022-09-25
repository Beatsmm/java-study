package com.example.javastudy.designMode.factoryMode;

public class PaymentApproval implements Approval{


    @Override
    public void approval() {
        System.out.println("执行一些付款审批的逻辑");
    }
}
