package com.fct.serp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON序列化发生异常 " + e.getMessage(), e);
        }
    }

//    public static <T> T fromJson(String json, Class<T> clazz) {
//        return mapper.convertValue(json, clazz);
//    }
}
