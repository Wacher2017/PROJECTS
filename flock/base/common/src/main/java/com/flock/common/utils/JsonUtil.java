package com.flock.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.Validate;

import java.io.IOException;

/**
 * JSON
 * Created by Chunming_Wang on 2019/12/23.
 */
public class JsonUtil {

    private JsonUtil() {}

    /**
     * 序列化成JSON格式字符串。
     *
     * @param obj 待序列化的对象。
     * @return JSON格式字符串。
     */
    public static String stringify(Object obj) {
        Validate.notNull(obj);

        ObjectMapper mapper = new ObjectMapper();
        SimpleFilterProvider sfp = new SimpleFilterProvider();
        sfp.addFilter("instance", new AllPropertyFilter());
        mapper.setFilters(sfp);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, Class<T> returnType) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleFilterProvider sfp = new SimpleFilterProvider();
        sfp.addFilter("instance", new AllPropertyFilter());
        mapper.setFilters(sfp);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        try {
            return mapper.readValue(json, returnType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, TypeReference<T> typeRef) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        try {
            T returnObject = mapper.readValue(json, typeRef);
            return returnObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
