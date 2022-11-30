package com.example.javastudy.designMode.policyMode;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private BigDecimal amount;

    private String payType;

}
