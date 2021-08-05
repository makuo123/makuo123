package com.stock.build.DesignModel.wrapper;

/**
 * ------------------------------------------------------
 * 装饰角色:
 *  持有一个构建对象的实例，并定义一个与抽象构件接口一致的接口
 * -------------------------------------------------------
 * @Author mk
 * @Date 2021/6/24 14:19
 * @Version 1.0
 */
public abstract class WrapperCooker implements CookDinner{

    protected CookDinner cookDinner;
}
