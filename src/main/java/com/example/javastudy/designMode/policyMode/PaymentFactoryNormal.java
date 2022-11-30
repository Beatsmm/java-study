package com.example.javastudy.designMode.policyMode;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactoryNormal {

    private static final Map<PaymentEnum, Payment> payStrategies = new HashMap<>();
    static {
        payStrategies.put(PaymentEnum.WX, new WechatPay());
        payStrategies.put(PaymentEnum.ALIPAY, new AliPay());
        payStrategies.put(PaymentEnum.BANK_CARD, new BankCardPay());
    }

    public static Payment getPayment(PaymentEnum payType) {
        if (payType == null) {
            throw new IllegalArgumentException("pay type is empty.");
        }
        if (!payStrategies.containsKey(payType)) {
            throw new IllegalArgumentException("pay type not supported.");
        }
        return payStrategies.get(payType);
    }

}
