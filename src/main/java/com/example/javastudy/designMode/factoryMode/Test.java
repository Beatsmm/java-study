package com.example.javastudy.designMode.factoryMode;

public class Test {

    /**
     * 背景：模拟公司中的审批，审批层级节点是否通过是通过Kafka监听然后在消费，所有的审批都会监听，因为
     * 飞书审批会返回审批的类型（code）在此用123代替，通过工程模式来解耦，维护
     */
    public static void main(String[] args) {
        Test test = new Test();
        ApprovalFactory factory = new ApprovalFactory();
        factory.approvalFactory(1).approval();


    }
}
