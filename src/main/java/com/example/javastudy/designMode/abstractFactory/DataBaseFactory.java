package com.example.javastudy.designMode.abstractFactory;

public class DataBaseFactory implements IDataFactory {

    private boolean isRedis;
    private boolean isMongoDB;


    public IDataBase getDataBase(){
        if (isMongoDB){
            return new MongoDBCache();
        }
        if (isRedis){
            return new RedisCache();
        }
        return new MysqlCache();
    }

    @Override
    public IDataBase getDataSelect() {
        return null;
    }

    @Override
    public LocalDataCache getLocalSelect() {
        return null;
    }
}
