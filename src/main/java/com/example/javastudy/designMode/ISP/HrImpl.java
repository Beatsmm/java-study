package com.example.javastudy.designMode.ISP;

public class HrImpl implements Hr, Staff{
    @Override
    public void clockIn(String name) {
        System.out.println(name + "打卡");
    }

    @Override
    public void recruit() {
        System.out.println("招聘");
    }


}
