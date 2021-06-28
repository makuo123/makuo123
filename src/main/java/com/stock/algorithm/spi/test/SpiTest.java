package com.stock.algorithm.spi.test;

import com.stock.algorithm.spi.SpiService;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author mk
 * @Date 2021/6/1 14:58
 * @Version 1.0
 */
public class SpiTest {

    @Test
    public void test() {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = load.iterator();
        while (iterator.hasNext()){
            SpiService next = iterator.next();
            next.test();
        }
    }
}
