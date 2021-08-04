package com.stock.build.DesignModel.observer;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ======================
 * 观察者模式（发布-订阅模式）
 * ======================
 * ----------
 * 抽象主题角色
 * ----------
 * ******************************
 * 抽象主题把所有观察者对象保存在一个集合中
 * 每个主题都可以有任意数量的观察者
 * 抽象主题提供 添加/删除 观察者的方法，通知所有观察者变动消息的方法
 * ******************************
 * @Author mk
 * @Date 2021/6/25 10:11
 * @Version 1.0
 */
public abstract class Subject {

    private List<Observer> list;

    public void addObserver(Observer observer){
        if (CollectionUtils.isEmpty(list)){
            list = new ArrayList<>();
        }
        list.add(observer);
    }

    public void deteleObserver(Observer observer){
        list.remove(observer);
    }

    public void notifyObservers(String newState){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).update(newState);
            System.out.println(i+" 号更新成功！");
        }
    }
}
