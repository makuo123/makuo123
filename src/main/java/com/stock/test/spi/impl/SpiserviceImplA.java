package com.stock.test.spi.impl;

import com.stock.test.spi.SpiService;

/**
 * @Author mk
 * @Date 2021/6/1 14:53
 * @Version 1.0
 */
public class SpiserviceImplA implements SpiService {

    @Override
    public void test() {
        System.out.println("==== SpiserviceImplA ===");
    }
}
