package com.stock.thread.threadlocal;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalThread extends Thread{

    private static AtomicInteger ai = new AtomicInteger();

    public ThreadLocalThread(String name) {
        super(name);
    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < 3; i++)
            {
                Tools.threadLocal.set(ai.addAndGet(1) + "");
                Thread.sleep(200);
                System.out.println(this.getName() + " get value--->" + Tools.threadLocal.get());


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadLocalThread t1 = new ThreadLocalThread("t1");
        ThreadLocalThread t2 = new ThreadLocalThread("t2");
        ThreadLocalThread t3 = new ThreadLocalThread("t3");
        t1.start();
        t2.start();
        t3.start();
    }

}
