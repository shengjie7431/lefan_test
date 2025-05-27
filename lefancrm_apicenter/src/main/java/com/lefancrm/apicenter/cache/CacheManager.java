package com.lefancrm.apicenter.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {

    private static Map<String, String> cacheMap = new HashMap<String, String>();

    private CacheManager() {

    }

    public static void putCache(String key, String value) {
        cacheMap.put(key, value);
    }

    public static String getCache(String key) {
        return cacheMap.get(key);
    }

}
