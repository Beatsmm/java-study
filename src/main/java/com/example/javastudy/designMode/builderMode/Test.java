package com.example.javastudy.designMode.builderMode;

/**
 * 模拟商品对不同级别会员的一些优惠场景，优惠场景如下
 * 1、优先下单等级
 * 2、优惠卷折扣
 * 3、返现金额
 *会员有
 * 1、普通会员
 * 2、白金会员
 * 3、至尊会员
 */
public class Test {

    public static void main(String[] args) {
        WelfareBuilder builder1 = new WelfareBuilder()
                .addCashbackProportion(10)
                .addPriorityOrderLevel(20)
                .addCashbackProportion(25);
        System.out.println(builder1.build());
    }
}
