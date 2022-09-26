package com.example.javastudy.designMode.abstractFactory;

public class LocalDataFactory implements IDataFactory{

    private boolean isJvmCache;

    public LocalDataCache getLocalCache(){
        if (isJvmCache){
            return new JVMCache();
        }
        return new GuavaCache();
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
