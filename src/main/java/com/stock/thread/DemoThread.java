package com.stock.thread;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoThread implements Runnable{

    private Object object = new Object();

    @Override
    public void run() {
        System.out.println(" DemoThread run ! ");
    }

    @Test
    public void test() {
        int i = 0;
        this.run();

        TestThread testThread = new TestThread();
        /** 唤醒sleep需要调用interrupt()方法 */
        //TestThread.sleep(2000);
        //testThread.interrupt();

        /**
         * wait() 、notifyAll()方法需要在同一个锁的代码块内执行.
         * 未在代码块内调用会抛运行时异常 .
         */
        synchronized (object) {
            if (i != 0){
                object.notifyAll();
            }else {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        //testThread.start();
    }

    class TestThread extends Thread{
        ReentrantLock reentrantLock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReadWriteLock() {
            @Override
            public Lock readLock() {
                return null;
            }

            @Override
            public Lock writeLock() {
                return null;
            }
        };

        //PriorityQueue
    }
}
