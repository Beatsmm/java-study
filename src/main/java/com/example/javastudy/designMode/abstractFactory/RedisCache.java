package com.example.javastudy.designMode.abstractFactory;

public class RedisCache implements IDataBase{
    @Override
    public void insert() {
        System.out.println("redis 缓存");
    }
}
