package com.stock.build;

/**
 * @Author mk
 * @Date 2020/10/30 16:43
 * @Version 1.0
 */
public class Demo1 {

    private String a;
    private String b;

    public static Demo1 instance(){
        return new Demo1();
    }

    public Demo1 setData(String aa,String bbb){
        System.out.println(111);
        return this;
    }
}
