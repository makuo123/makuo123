package com.stock.build.DesignModel.adapter;

/**
 * 适配器模式
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
