package com.stock.build.DesignModel.observer;

/**
 * ------------
 * 抽象观察者角色
 * ------------
 * **************************
 * 为所有的具体观察者提供一个接口，
 * 在得到主题通知时更新自己
 * **************************
 * @Author mk
 * @Date 2021/6/25 10:21
 * @Version 1.0
 */
public interface Observer {
    void update(String newState);
}
