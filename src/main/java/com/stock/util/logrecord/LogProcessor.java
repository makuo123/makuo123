package com.stock.util.logrecord;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description
 * @Author makuo
 * @Date 2023/6/14 16:38
 **/
public class LogProcessor {
    public static void process(Object obj, Method method, Object[] args) {
        StringBuilder sb = new StringBuilder();
        // 获取类上的注解
        Loggable classAnnotation = obj.getClass().getAnnotation(Loggable.class);
        if (classAnnotation != null) {
            sb.append("类").append(obj.getClass().getName()).append(": ").append(classAnnotation.value()).append("; ");
        }
        // 获取方法上的注解
        Loggable methodAnnotation = method.getAnnotation(Loggable.class);
        if (methodAnnotation != null) {
            sb.append("方法").append(method.getName()).append(": ").append(methodAnnotation.value()).append("; ");
        }
        // 记录修改日志
        System.out.println("修改日志：" + sb.toString() + "修改参数：" + Arrays.toString(args));
    }
}
