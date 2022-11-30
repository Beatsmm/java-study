package com.example.javastudy.designMode.policyMode;

public enum PaymentEnum {

    WX("WX"),
    ALIPAY("ALIPAY"),
    BANK_CARD("BANK_CARD");

    String type;

    PaymentEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
