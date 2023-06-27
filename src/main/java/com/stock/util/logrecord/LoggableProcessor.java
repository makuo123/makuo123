package com.stock.util.logrecord;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description
 * @Author makuo
 * @Date 2023/6/14 16:50
 **/
@Component
public class LoggableProcessor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 在这里获取被代理方法的参数，并将其记录到日志中
        System.out.println(Arrays.toString(args));

        // 调用被代理方法
        Object result = method.invoke(obj, args);

        // 返回方法的返回值
        return result;
    }
}
