package com.example.javastudy.designMode.factoryMode;

public class ContractApproval implements Approval{
    @Override
    public void approval() {
        System.out.println("执行一些合同审批的逻辑");
    }
}
