package com.stock.test.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService{

    @Async
    @Override
    public void test1(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1执行。。。 : " + Thread.currentThread().getName());
    }

    @Async
    public void test2(){
        System.out.println("test2执行。。。 : " + Thread.currentThread().getName());
    }
}
