package com.example.javastudy.designMode.ocp;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Goods> list = new ArrayList<>();

    public void addGoods(Goods goods){
        list.add(goods);
    }

    public BigDecimal calTotalPrice(){
        BigDecimal sum = new BigDecimal("0");
        for (Goods goods : list) {
            sum = sum.add(goods.calPrice());
        }
        return sum;
    }
}
