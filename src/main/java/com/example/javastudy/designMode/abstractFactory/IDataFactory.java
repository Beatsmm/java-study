package com.example.javastudy.designMode.abstractFactory;

/**
 * 抽象工厂是一个超级工厂创建其他工厂，它是工厂模式的升级，工厂模式是一个产品结构，
 * 而抽象工厂是多个产品结构
 */
public interface IDataFactory {

    IDataBase getDataSelect();

    LocalDataCache getLocalSelect();

}
