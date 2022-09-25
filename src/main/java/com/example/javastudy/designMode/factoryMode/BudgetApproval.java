package com.example.javastudy.designMode.factoryMode;

import java.util.Map;

public class BudgetApproval implements Approval{
    @Override
    public void approval() {
        System.out.println("执行一些预算审批的逻辑");
    }
}
