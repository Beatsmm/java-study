package com.example.javastudy.designMode.templateMode.easy;

import java.util.HashMap;
import java.util.Map;

public class LocalCache extends AbstractSetting{
    private Map<String,String> cache = new HashMap<>();

    @Override
    protected String loadCache(String key) {
        return cache.get(key);
    }

    @Override
    protected void putInfoCache(String key, String value) {
        cache.put(key, value);
    }
}
