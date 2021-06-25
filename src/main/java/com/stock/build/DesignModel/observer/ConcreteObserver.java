package com.stock.build.DesignModel.observer;

/**
 * @Author mk
 * @Date 2021/6/25 10:28
 * @Version 1.0
 */
public class ConcreteObserver implements Observer{

    private String observerState;

    public String getObserverState() {
        return observerState;
    }

    @Override
    public void update(String newState) {
        observerState = newState;
        System.out.println("状态为：" + observerState);
    }
}
