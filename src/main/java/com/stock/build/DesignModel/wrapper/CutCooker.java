package com.stock.build.DesignModel.wrapper;

/**
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
