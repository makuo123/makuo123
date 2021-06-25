package com.stock.build.DesignModel.observer;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
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
