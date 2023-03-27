package com.example.javastudy.designMode.templateMode.easy;

public abstract class AbstractSetting {

    public final String getSetting(String key){
        String value = loadCache(key);
        if (value == null){
            value = getDataFromMySql();
            putInfoCache(key, value);
        }
        return value;
    }

    private String getDataFromMySql() {
        return "模拟数据";
    }

    protected abstract String loadCache(String key);

    protected abstract void putInfoCache(String key, String value);

}
