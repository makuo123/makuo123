package com.stock.build.DesignModel.observer;

/**
 * -------------
 * 具体观察者角色
 * -------------
 * ******************************************
 * 存储与主题的状态相关的状态。
 * 具体观察者角色实现抽象观察者角色所要求的更新接口，
 * 以便使本身的状态与主题的状态协调
 * ******************************************
 * @Author mk
 * @Date 2021/6/25 10:28
 * @Version 1.0
 */
public class ConcreteObserver implements Observer{

    private volatile String observerState;

    public String getObserverState() {
        return observerState;
    }

    @Override
    public void update(String newState) {
        observerState = newState;
        System.out.println("状态为：" + observerState);
    }
}
