package com.stock.build.DesignModel;

/**
 * 单例模式
 *
 * @Author mk
 * @Date 2021/6/23 15:31
 * @Version 1.0
 */
public class DemoSingleton {

    /**
     * 1、不能使用构造方法创建对象
     * 2、提供唯一获取对象接口
     * 3、如果没有实例化则实例化，已经实例化则直接返回
     * */

    private static DemoSingleton singleton = null;

    private DemoSingleton() {
    }

    public static DemoSingleton getInstance(){
        if (singleton == null){
            synchronized (DemoSingleton.class){
                if (singleton == null){
                    singleton = new DemoSingleton();
                }
            }
        }
        return singleton;
    }

}
