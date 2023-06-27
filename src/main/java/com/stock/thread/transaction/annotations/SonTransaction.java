package com.stock.thread.transaction.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 多线程事务注解: 子事务
 * @Author makuo
 * @Date 2023/6/27 14:48
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SonTransaction {
    String value() default "";
}
