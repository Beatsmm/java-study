package com.example.javastudy.designMode.ocp;

import java.math.BigDecimal;

public class Book implements Goods{

    private String name;

    private BigDecimal price;

    public Book(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public BigDecimal calPrice() {
        return price;
    }
}
