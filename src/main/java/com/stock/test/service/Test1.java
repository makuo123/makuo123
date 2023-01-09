package com.stock.test.service;

import com.stock.test.AbstractTest;

public class Test1 extends AbstractTest {
    @Override
    public String test(String a) {
        return "sdf";
    }

    @Override
    public String getTest() {
        System.out.println(111);
        return null;
    }
}
