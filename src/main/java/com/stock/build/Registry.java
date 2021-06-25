package com.stock.build;

import org.apache.http.config.Lookup;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author mk
 * @Date 2020/10/30 16:04
 * @Version 1.0
 */
public class Registry<I> implements Lookup {
    private final Map<String, I> map;

    Registry(Map<String, I> map) {
        this.map = new ConcurrentHashMap(map);
    }

    @Override
    public I lookup(String key) {
        return key == null ? null : this.map.get(key.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}
