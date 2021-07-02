package com.stock.build;

import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

/**
 * @Author mk
 * @Date 2020/10/30 16:43
 * @Version 1.0
 */
public class Demo1 {

    private String a;
    private String b;

    public static Demo1 instance(){
        return new Demo1();
    }

    public Demo1 setData(String aa,String bbb){
        System.out.println(111);
        return this;
    }

    @Test
    public void tryFinally(){
        try {
            int a = 1/0;
            System.out.println(a);
            return;
        } catch (Exception e) {
            System.out.println("catch");
            return;
        } finally {
            System.out.println("finally");
        }
    }

    class DemoLister implements MessageSource{

        @Override
        public String getMessage(String s, Object[] objects, String s1, Locale locale) {
            return null;
        }

        @Override
        public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
            return null;
        }

        @Override
        public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
            return null;
        }
    }
}
