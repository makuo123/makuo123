package com.stock.algorithm;

/**
 * @Author mk
 * @Date 2020/10/27 15:12
 * @Version 1.0
 */
public class MethordTest {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int sum(){
        return a+b;
    }

    protected void printStr(String str){
        System.out.println("父类方法");
    }
}
