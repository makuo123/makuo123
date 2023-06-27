package com.stock.util.logrecord;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author makuo
 * @Date 2023/6/14 17:05
 **/
@Component
@Aspect
public class LoggableAspect {
    @Autowired
    private LoggableProcessor loggableProcessor;

    @Around("@annotation(com.stock.util.logrecord.Loggable)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return loggableProcessor.intercept(joinPoint.getTarget(),
                joinPoint.getClass().getDeclaredMethods()[0], joinPoint.getArgs(),
                null);
    }
}
