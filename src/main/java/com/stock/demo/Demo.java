package com.stock.demo;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author mk
 * @Date 2021/7/6 16:27
 * @Version 1.0
 */
public class Demo {

    @Test
    public void test(){
        List<String> stringList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();
        new ReentrantLock();
        new ArrayBlockingQueue<List>(19);
        new ScheduledThreadPoolExecutor(10);
        System.out.println(stringList.getClass() == integerList.getClass());
    }

    @Test
    public void test1(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行时间：" );
            }
        }, 1000L);
    }

    public static void main(String[] args) {

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors = " + processors);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int count = 0;
                ++count;
                System.out.println("执行时间：" + count);
            }
        }, 3000l, 3000L);
    }
}
