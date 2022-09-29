package com.example.javastudy.designMode.builderMode;


public class Welfare {

    // 优先下单等级
    private Integer priorityOrderLevel;

    // 优惠卷折扣
    private Float discountOnCoupon;

    // 返现百分比
    private Integer cashbackProportion;

    public Integer getPriorityOrderLevel() {
        return priorityOrderLevel;
    }

    public void setPriorityOrderLevel(Integer priorityOrderLevel) {
        this.priorityOrderLevel = priorityOrderLevel;
    }

    public Float getDiscountOnCoupon() {
        return discountOnCoupon;
    }

    public void setDiscountOnCoupon(Float discountOnCoupon) {
        this.discountOnCoupon = discountOnCoupon;
    }

    public Integer getCashbackProportion() {
        return cashbackProportion;
    }

    public void setCashbackProportion(Integer cashbackProportion) {
        this.cashbackProportion = cashbackProportion;
    }

    @Override
    public String toString() {
        return "Welfare{" +
                "priorityOrderLevel=" + priorityOrderLevel +
                ", discountOnCoupon=" + discountOnCoupon +
                ", cashbackProportion=" + cashbackProportion +
                '}';
    }
}
