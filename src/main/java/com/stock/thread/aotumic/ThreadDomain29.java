package com.stock.thread.aotumic;

import com.stock.thread.threadlocal.Tools;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDomain29 {

    public static AtomicInteger aiRef = new AtomicInteger();

    public synchronized void addNum()
    {

        System.out.println(Thread.currentThread().getName()+" 加了100之后的结果：" +
                aiRef.addAndGet(100));

        aiRef.getAndAdd(1);
    }
}
