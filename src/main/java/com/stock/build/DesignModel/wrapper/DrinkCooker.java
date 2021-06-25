package com.stock.build.DesignModel.wrapper;

/**
 * @Author mk
 * @Date 2021/6/24 14:25
 * @Version 1.0
 */
public class DrinkCooker extends WrapperCooker{

    public DrinkCooker(CookDinner cookDinner) {
        this.cookDinner = cookDinner;
    }

    @Override
    public void cook() {
        cookDinner.cook();
        System.out.println("喝酒");
    }
}
