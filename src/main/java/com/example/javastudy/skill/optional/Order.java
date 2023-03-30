package com.example.javastudy.skill.optional;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {

    private Long orderNo;

    private String address;

    private LocalDateTime paymentTime;

    private Goods goods;

    private Logistics logistics;
}
