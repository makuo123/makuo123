package com.stock.build.DesignModel.observer;

/**
 * @Author mk
 * @Date 2021/6/25 10:25
 * @Version 1.0
 */
public class ConcreteSubject extends Subject{

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("状态更新为：" + state + ",更新所有观察者状态！");
        this.notifyObservers(state);
        System.out.println("更新成功！");
    }
}
