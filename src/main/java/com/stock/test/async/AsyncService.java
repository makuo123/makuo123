package com.stock.test.async;

/**
 * 验证异步执行注解@Async，需要在启动类上加上@EnableAsync
 */
public interface AsyncService {

    void test1();
    void test2();
}
