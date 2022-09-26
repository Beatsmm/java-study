package com.example.javastudy.designMode.abstractFactory;

public class GuavaCache implements LocalDataCache{
    @Override
    public void add() {
        System.out.println("Guava 缓存");
    }
}
