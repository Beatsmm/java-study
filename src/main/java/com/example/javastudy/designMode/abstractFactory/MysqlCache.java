package com.example.javastudy.designMode.abstractFactory;

public class MysqlCache implements IDataBase{
    @Override
    public void insert() {
        System.out.println("mysql 缓存");
    }
}
