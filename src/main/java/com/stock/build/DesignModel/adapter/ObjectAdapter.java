package com.stock.build.DesignModel.adapter;

/**
 * =============
 * 适配器模式
 * =============
 * **************************************************************
 *  将一个类的接口转变成客户端所期待的另一种接口，
 *  使得原本因为接口不匹配而无法在一起工作的两个类可以一起工作
 *  **************************************************************
 * @Author mk
 * @Date 2021/6/24 11:34
 * @Version 1.0
 */
public class ObjectAdapter {

    private ObjectAdaptee objectAdaptee;

    public ObjectAdapter(ObjectAdaptee objectAdaptee) {
        this.objectAdaptee = objectAdaptee;
    }

    public void method1(){
        this.objectAdaptee.method1();
    }

    public void mehtod2(){
        System.out.println("method2");
    }
}
