package com.stock.demo;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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

    /*public static void main(String[] args) {

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
    }*/


    private static String hexStr = "3131303236303832393743323437333530303231340000000000000000";

    public static void main(String[] args) {
        String collectID = "";
        byte[] bytes = hexStringToByte(hexStr);
        System.out.println(Arrays.toString(bytes));
        for (int i = 0; i < bytes.length; i++) {
            collectID += (char) bytes[i];
        }
        System.out.println(collectID);

    }

    /**
     * 16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        byte[] b = new byte[hex.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hex.charAt(j++);
            char c1 = hex.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;

    }
}
