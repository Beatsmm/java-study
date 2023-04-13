package com.example.javastudy.skill.json;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class JsonObject {

    private Map<String, Object> value;

    private JsonObject(String json) {
        this.value = JacksonUtil.fromJson(json, Map.class);
    }

    public static JsonObject parse(String json) {
        return new JsonObject(json);
    }

    public <T> T getObject(String keys, Class<T> valueType) {
        if (StringUtils.isBlank(keys) || value == null) {
            return null;
        }
        String[] ks = keys.split("\\.");
        Map<String, Object> curVal = value;
        for (int i = 0; i < ks.length - 1; i++) {
            Object val = curVal.get(ks[i]);
            if (val == null) {
                return null;
            }
            value = (Map<String, Object>) val;
        }
        return (T)value.get(ks[ks.length - 1]);
    }

    public String getString(String keys) {
        return getObject(keys, String.class);
    }

    public Integer getInteger(String keys) {
        return getObject(keys, Integer.class);
    }

    public Long getLong(String keys) {
        return getObject(keys, Long.class);
    }

    public <T> List<T> getList(String keys, Class<T> valueType) {
        List<Object> list = getObject(keys, List.class);
        if (list == null) {
            return null;
        }
        return list.stream().map(item -> JacksonUtil.fromJson(JacksonUtil.toString(item), valueType)).collect(Collectors.toList());
    }

}
