package com.stock.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/3 9:12
 **/
public class InstanceCache {

    private static InstanceCache instanceCache = new InstanceCache();

    private static Map<String, Object> cache = new HashMap<>();

    private InstanceCache() {
    }

    public static InstanceCache getInstance(){
        return instanceCache;
    }

    public Map getCache(){
        return cache;
    }

    public void setCache(String key , String value){
        cache.put(key, value);
    }
}
