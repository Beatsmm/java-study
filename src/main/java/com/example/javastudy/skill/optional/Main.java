package com.example.javastudy.skill.optional;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {


    public static void main(String[] args) {
        Order order = getOrder();
        OrderVO vo = new OrderVO();
        vo.setAddress(order.getAddress());
        vo.setOrderNo(order.getOrderNo());
        Optional.ofNullable(order.getGoods()).ifPresent(v -> {
            vo.setGoodsName(order.getGoods().getGoodsName());
            vo.setPrice(order.getGoods().getPrice());
        });
//        Optional.ofNullable(order.getLogistics()).ifPresent(v -> {
//            vo.setLogisticsNo(order.getLogistics().getLogisticsNo());
//            vo.setLogisticsName(order.getLogistics().getLogisticsName());
//        });
        String logisticsName = Optional.ofNullable(order)
                .map(Order::getLogistics)
                .map(Logistics::getLogisticsName)
                .orElse(null);
        vo.setLogisticsName(logisticsName);
        System.out.println(vo);
    }

    private static Order getOrder() {
        Order order = new Order();
        order.setOrderNo(123456789l);
        order.setAddress("北京");
        order.setGoods(new Goods("Mac",new BigDecimal("10000")));

        Logistics logistics = new Logistics(12345677,"顺丰");
        return order;
    }
}
