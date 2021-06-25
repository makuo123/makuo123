package com.stock.service.noif;

import com.stock.po.Order;

/**
 * 策略模式 抽象接口
 * @Author mk
 * @Date 2021/5/27 14:59
 * @Version 1.0
 */
public interface OrderService2 {
    Order orderSync(Order order);
}
