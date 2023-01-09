package com.stock.test;

import com.stock.test.service.Test1;

import java.io.FileInputStream;

public abstract class AbstractTest {

    public String getTest(){
        System.out.println(2222);
        return this.test("s");
    }

    public abstract String test(String a);

    public static void main(String[] args) {
        Test1 test1 = null;
        try (FileInputStream fileInputStream = new FileInputStream("")){
            test1 = new Test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(test1.getTest());
    }
}
