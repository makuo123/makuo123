package com.stock.algorithm;

/**
 * @Author mk
 * @Date 2021/6/22 11:48
 * @Version 1.0
 */
public class SubTest extends MethordTest{

    @Override
    protected void printStr(String str) {
        System.out.println("开始子类方法");
        super.printStr(str);
        System.out.println("结束子类方法");
    }


    public static void main(String[] args) {
        SubTest subTest = new SubTest();
        subTest.printStr("调用父类方法");
    }
}
