package com.stock.build;

import org.apache.http.util.Args;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mk
 * @Date 2020/10/30 15:28
 * @Version 1.0
 */
public final class RegistBuilder<I> {
    private static RegistBuilder registBuilder;
    private final Map<String, I> items = new HashMap<>();

    private RegistBuilder() {
    }

    //获取单例BuildTest对象的方法
    public static <I> RegistBuilder<I> create() {
        if (registBuilder == null) {
            synchronized (RegistBuilder.class) {
                if (registBuilder == null) {
                    registBuilder = new RegistBuilder();
                }
            }
        }
        return registBuilder;
    }

    //注入元素
    public RegistBuilder<I> register(String id, I item) {
        Args.notEmpty(id, "Id");
        Args.notNull(item, "Item");
        this.items.put(id, item);
        return this;
    }

    public Registry build() {
        return new Registry(this.items);
    }


}
