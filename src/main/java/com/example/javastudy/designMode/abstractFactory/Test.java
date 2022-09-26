package com.example.javastudy.designMode.abstractFactory;

/**
 * 现在业务背景是我们可以选择本地缓存或者数据库缓存方式
 * 本地缓存可以有JVM缓存和Guava缓存
 * 数据库缓存可以有Mysql,Redis,MongoDB缓存
 */
public class Test {

    public static void main(String[] args) {
        IDataBase dataBase = new DataBaseFactory().getDataBase();
        dataBase.insert();
        LocalDataCache localCache = new LocalDataFactory().getLocalCache();
        localCache.add();
    }
}
