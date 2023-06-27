package com.stock.po;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author mk
 * @Date 2021/4/19 16:47
 * @Version 1.0
 */
public class A {

    public void initTest(TestInterface testInterface){

    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2");

        List<String> list1 = Arrays.asList("1", "2", "3");

        Set<String> collect = list1.stream().filter(a -> !list.contains(a)).collect(Collectors.toSet());

        System.out.println(collect);
    }
}
