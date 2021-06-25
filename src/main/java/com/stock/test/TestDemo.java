package com.stock.test;

import com.stock.entity.TestValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author mk
 * @Date 2020/10/27 15:14
 * @Version 1.0
 */
public class TestDemo {

    private static  int A_VALUE;
    public static void main(String[] args) {
        /*MethordTest methordTest = new MethordTest();
        methordTest.setA(1);
        methordTest.setB(2);
        int sum = methordTest.sum();
        System.out.println(sum);
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        ThreadLocal threadLocal = new ThreadLocal();
        Object o = threadLocal.get();
        System.out.println(o);

        String s = "aaa";
        System.out.println(s.replace(s, "bbb"));
        int a = 0;


        a = md(a);
        System.out.println(a);*/

        /*RegistBuilder.create().register("1", "44").register("2", "22").build();

        RegistBuilder<Object> objectRegistBuilder = RegistBuilder.create();
        RegistBuilder<Object> objectRegistBuilder1 = RegistBuilder.create();
        System.out.println(objectRegistBuilder == objectRegistBuilder1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
        stringBuilder.append(2);
        System.out.println(stringBuilder.toString());*/

        /*List list = new ArrayList();
        for (Object o : list) {

        }
        Map map = new HashMap();
        System.out.println(A_VALUE);
        System.out.println(list == null);
        md(2);*/

        /** 这个算法的思路就是将该数字的最高非0位后面全置为1！
         * 保证此数的最小2的整数幂
         * hashmap扩容 -1 是为了规避掉本身为2的整数幂的数据
         * 其利用了“拷贝”的方式： */
        int c = 5;

        c |= c>>>1;
        c |= c>>>2;
        c |= c>>>4;
        c |= c>>>8;
        c |= c>>>16;

        System.out.println(c+1);

        TestValue value = new TestValue();
        List<TestValue> list = new ArrayList<>();
        list.add(new TestValue("4401","广州1"));
        list.add(new TestValue("4405","广州2"));
        list.add(new TestValue("4201","广州3"));
        list.add(new TestValue("4101","广州4"));

        List<TestValue> collect = list.stream().filter(o -> o.getCode().startsWith("44")&&o.getValue().equals("guangz")).collect(Collectors.toList());
        System.out.println(collect.toArray());

        System.out.println("123456".substring(0, 2));
    }

    private static int md(final int a) {

       /* for (int i = 0; i < 10; i++) {
            int n;
            if (i % 2 == 0) {
                n = 10;
            } else {
                n = 20;
            }
            System.out.println(n);
        }*/

        //a =5;
        System.out.println(a);
        return a;
    }


    @Test
    public void test(){
        int a = 10;
        int b = 20;

        //a = a^b^a;
        System.out.println(a);
        System.out.println(String.valueOf(a ^ b ^ b) + " = 10");
        System.out.println(String.valueOf(b ^ a ^ a) + " = 20");
    }

    @Test
    public void test1(){
        int a = 3 % 16;
        int b = 3 & 15;
        System.out.println(a == b);
    }
}
