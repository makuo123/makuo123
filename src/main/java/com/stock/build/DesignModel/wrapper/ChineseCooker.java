package com.stock.build.DesignModel.wrapper;

/**
 * ----------------------
 * 具体装饰角色:
 *  负责给构建对象贴上附加的责任
 * ----------------------
 * @Author mk
 * @Date 2021/6/24 14:17
 * @Version 1.0
 */
public class ChineseCooker implements CookDinner{
    @Override
    public void cook() {
        System.out.println("煮米饭");
    }
}
