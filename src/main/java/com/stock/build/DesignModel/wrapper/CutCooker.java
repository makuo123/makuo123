package com.stock.build.DesignModel.wrapper;

/**
 * --------------------------------------------
 * 具体构件角色:
 *   定义一个将要接受附加责任的类
 * --------------------------------------------
 * @Author mk
 * @Date 2021/6/24 14:22
 * @Version 1.0
 */
public class CutCooker extends WrapperCooker{

    public CutCooker(CookDinner cookDinner) {
        this.cookDinner = cookDinner;
    }

    @Override
    public void cook() {
        System.out.println("切菜");
        cookDinner.cook();
    }
}
