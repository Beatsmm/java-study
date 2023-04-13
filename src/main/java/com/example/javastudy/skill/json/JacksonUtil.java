package com.example.javastudy.skill.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class JacksonUtil {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")));
        MAPPER.registerModule(timeModule);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        if (isBlank(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <E, T extends Collection<E>> T fromJson(String json, Class<? extends Collection> collectionType, Class<E> valueType) {
        if (isBlank(json)) {
            json = "[]";
        }

        try {
            return (T) MAPPER.readValue(json, TypeFactory.defaultInstance().constructCollectionType(collectionType, valueType));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V, T extends Map<K, V>> T fromJson(String json, Class<? extends Map> mapType, Class<K> keyType, Class<V> valueType) {
        if (isBlank(json)) {
            json = "{}";
        }

        try {
            return (T) MAPPER.readValue(json, TypeFactory.defaultInstance().constructMapType(mapType, keyType, valueType));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取Tree Model
     */
    public static JsonNode readTree(String json) {
        try {
            if (json == null) {
                return null;
            }
            return MAPPER.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
