package com.example.javastudy.designMode.decoratorMode.easy;

public class Iphone4s implements Phone{

    @Override
    public void takePhotos() {
        System.out.println("拍照");
    }

    @Override
    public void call() {
        System.out.println("打电话");
    }
}
