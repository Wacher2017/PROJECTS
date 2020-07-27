package com.lms.common.core.context;

import org.apache.commons.collections.MapUtils;

import java.util.Map;

public class StaticDataContext {

    private static InheritableThreadLocal<Map<String, Object>> staticDataContext = new InheritableThreadLocal<>();

    public static void setStaticDataContext(Map<String, Object> value) {
        staticDataContext.set(value);
    }

    public static Map<String, Object> getStaticDataContext() {
        return staticDataContext.get();
    }

    public static void removeStaticDataContext() {
        staticDataContext.remove();
    }

    public static void removeStaticDataContext(String key) {
        Map<String, Object> map = staticDataContext.get();
        if(MapUtils.isNotEmpty(map) && map.containsKey(key)) {
            map.remove(key);
        }
    }

}
