package com.stock.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mk
 * @Date 2021/7/6 16:27
 * @Version 1.0
 */
public class Demo {

    @Test
    public void test(){
        List<String> stringList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();
        System.out.println(stringList.getClass() == integerList.getClass());
    }
}
