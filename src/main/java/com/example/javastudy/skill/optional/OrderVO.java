package com.example.javastudy.skill.optional;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderVO {

    private Long orderNo;

    private String address;

    private String goodsName;

    private BigDecimal price;

    private Integer logisticsNo;

    private String logisticsName;

    private LocalDateTime paymentTime;
}
