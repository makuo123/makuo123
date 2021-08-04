package com.stock.build.DesignModel.observer;

/**
 * -----------
 * 具体主题角色
 * -----------
 * **************************
 * 将有关状态存入具体观察者对象，
 * 在具体主题的内部状态改变时，
 * 给所有登记过的观察者发出通知。
 * **************************
 * @Author mk
 * @Date 2021/6/25 10:25
 * @Version 1.0
 */
public class ConcreteSubject extends Subject{

    private volatile String state;

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
