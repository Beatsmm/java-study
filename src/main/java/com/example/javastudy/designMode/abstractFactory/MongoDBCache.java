package com.example.javastudy.designMode.abstractFactory;

public class MongoDBCache implements IDataBase{
    @Override
    public void insert() {
        System.out.println("mongoDB cache");
    }
}
