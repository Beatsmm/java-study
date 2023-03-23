package com.example.javastudy.designMode.decoratorMode.easy;

public class PhoneDecorator implements Phone{

    private final Phone phone;

    public PhoneDecorator(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void takePhotos() {
        phone.takePhotos();
        System.out.println("给4s装备了投屏的功能，让照片可以投放到电视上");
    }

    @Override
    public void call() {
        phone.call();
        System.out.println("可以用nfc功能了，再也不用打电话给别人换现金购买地铁票了");
    }
}
