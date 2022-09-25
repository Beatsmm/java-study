package com.example.javastudy.designMode.factoryMode;

public class ApprovalFactory {
    public Approval approvalFactory(Integer type) {
//        if (null == type) return null;
//        if (1 == type) return new BudgetApproval();
//        if (2 == type) return new ContractApproval();
//        if (3 == type) return new PaymentApproval();
//        throw new RuntimeException("未知类型");
        switch (type){
            case 1:
                return new BudgetApproval();
            case 2:
                return new ContractApproval();
            case 3:
                return new PaymentApproval();
            default:
                throw new RuntimeException("未知类型");
        }
    }
}
