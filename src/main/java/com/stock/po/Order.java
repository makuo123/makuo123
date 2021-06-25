package com.stock.po;

import com.stock.enums.OrderType;

import java.nio.file.FileStore;

/**
 * @Author mk
 * @Date 2021/5/27 15:18
 * @Version 1.0
 */
public class Order {

    private int discount;

    private OrderType orderType;


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
