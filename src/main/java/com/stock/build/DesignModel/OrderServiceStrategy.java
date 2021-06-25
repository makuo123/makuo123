package com.stock.build.DesignModel;

import com.stock.enums.OrderType;
import com.stock.po.Order;
import com.stock.service.noif.OrderService2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mk
 *   com.stock.service.noif.impl.M01OrderServiceImpl
 *  设计模式中的策略模式
 *  解决if-else过多的问题
 *
 * @Date 2021/5/27 15:20
 * @Version 1.0
 */
public class OrderServiceStrategy {

    private static Map<String, String> map = new HashMap() {
        {
            put("M01", "com.stock.service.noif.impl.M01OrderServiceImpl");
//            put("M02", "lang.service.M02OrderServiceImpl");
//            put("M03", "lang.service.M03OrderServiceImpl");
        }
    };

    public static Map<String, String> getMap() {
        return map;
    }

    /**
         2.请修改下面这段代码，目标是减少if-else，并且在以后增加新的折扣类型时做到易于扩展

         public class OrderService {

             public double discount(Order order) {
             double discount = 0.0;
             OrderType type = order.getType();
             if (type.equals(OrderType.NORMAL)) { // 普通订单
             //...省略折扣计算算法代码
             } else if (type.equals(OrderType.GROUPON)) { //  团购订单
             //...省略折扣计算算法代码
             } else if (type.equals(OrderType.PROMOTION)) {  // 促销订单
             //...省略折扣计算算法代码
             }
             return discount;
             }
         }
     */

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        OrderServiceStrategy orderServiceStrategy = new OrderServiceStrategy();
        Order order = new Order();
        order.setOrderType(OrderType.M01);

        orderServiceStrategy.discount(order);
    }


    public double discount(Order order) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (!OrderServiceStrategy.getMap().containsKey(order.getOrderType().name())) {
            new RuntimeException("不支持的业务类型");
        }

        String clazzName = OrderServiceStrategy.getMap().get(order.getOrderType().name());

        Class classw = Class.forName(clazzName);

        OrderService2 orderService2 = (OrderService2) classw.newInstance();

        Order orderSync = orderService2.orderSync(order);

        double discount1 = orderSync.getDiscount();

        System.out.println(discount1);

        return discount1;
    }

}
