package com.stock.service.noif.impl;

import com.stock.po.Order;
import com.stock.service.noif.OrderService2;

/**
 * @Author mk
 * @Date 2021/5/27 15:05
 * @Version 1.0
 */
public class M01OrderServiceImpl implements OrderService2 {

    @Override
    public Order orderSync(Order order) {
        order.setDiscount(1);
        System.out.println("进来了");
        System.out.println(order.toString());
        return order;
    }

}
