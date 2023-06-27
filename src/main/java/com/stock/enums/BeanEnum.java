package com.stock.enums;

import com.stock.service.serviceImpl.StockServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @Description
 * @Author makuo
 * @Date 2023/4/11 11:44
 **/
@AllArgsConstructor
public enum BeanEnum {
    INSTANCE(new StockServiceImpl());

    public final StockServiceImpl service;

    /*BeanEnum(StockServiceImpl service){
        this.service = service;
    }*/

    public void doSomething() {
        service.findStockList();
    }

    public static void main(String[] args) {
//        BeanEnum.INSTANCE.service.findStockList();
        System.out.println(32%12);
    }
}
