package com.example.javastudy.designMode.builderMode;

public class WelfareBuilder {

    private Welfare welfare = new Welfare();

    public WelfareBuilder addPriorityOrderLevel(Integer level){
        welfare.setPriorityOrderLevel(level);
        return this;
    }

    public WelfareBuilder addDiscountOnCoupon(Float discountOnCoupon){
        welfare.setDiscountOnCoupon(discountOnCoupon);
        return this;
    }

    public WelfareBuilder addCashbackProportion(Integer cashbackProportion){
        welfare.setCashbackProportion(cashbackProportion);
        return this;
    }

    public Welfare build(){
        return this.welfare;
    }
}
