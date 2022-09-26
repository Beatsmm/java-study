package com.example.javastudy.designMode.abstractFactory;

public class JVMCache implements LocalDataCache{
    @Override
    public void add() {
        System.out.println("JVM 缓存");
    }
}
