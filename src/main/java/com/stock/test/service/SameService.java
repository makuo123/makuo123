package com.stock.test.service;

/**
 * 接口有多个实现类，处理方式
 *1、一个实现类加 @primary 注解，默认实现类
 * 2、实现类的命名，引用注入时指定实现类名称，@resource(name = "xxx")
 */
public interface SameService {
    void save();
}
