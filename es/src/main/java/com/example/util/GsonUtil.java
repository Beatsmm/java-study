package com.example.util;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtil {

    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .excludeFieldsWithModifiers(Modifier.PROTECTED)
                .disableHtmlEscaping()
                .create();
    }

    public static String toString(Object object){
        if (object == null){
            return null;
        }
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> valueType){
        if (StringUtils.isBlank(json)){
            return null;
        }
        return gson.fromJson(json, valueType);
    }

    public static <T> T fromJson(String json, Type valueType){
        if (StringUtils.isBlank(json)){
            return null;
        }
        return gson.fromJson(json, valueType);
    }

    public static <T> List<T> toList(String json, Class<T> cls){
        if (StringUtils.isBlank(json)){
            json = "[]";
        }
        List<T> list = new ArrayList<>();
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            list.add(gson.fromJson(jsonElement, cls));
        }
        return list;
    }

    public static Gson getGson(){
        return gson;
    }
}
