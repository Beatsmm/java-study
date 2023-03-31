package com.example.javastudy.designMode.dip;

public class Main {

    public static void main(String[] args) {
        MeiTuan meiTuanAli = new MeiTuan();
        meiTuanAli.takeOut(new AliPay());

        MeiTuan meiTuanWx = new MeiTuan();
        meiTuanWx.takeOut(new WXPay());
    }
}
