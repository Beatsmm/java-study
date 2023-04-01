package com.example.javastudy.designMode.ocp;

import java.math.BigDecimal;

public class Cloth implements Goods{

    private String size;

    private BigDecimal price;


    public Cloth(String size, BigDecimal price) {
        this.size = size;
        this.price = price;
    }

    @Override
    public BigDecimal calPrice() {
        return price;
    }


}
