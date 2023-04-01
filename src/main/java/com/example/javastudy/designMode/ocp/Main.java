package com.example.javastudy.designMode.ocp;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Book book = new Book("代码简洁之道", new BigDecimal("55"));
        Cloth cloth = new Cloth("Adidas", new BigDecimal("1000"));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addGoods(book);
        shoppingCart.addGoods(cloth);
        System.out.println("购物车总价为:"+shoppingCart.calTotalPrice());
    }
}
