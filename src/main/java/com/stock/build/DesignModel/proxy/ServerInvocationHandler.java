package com.stock.build.DesignModel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author mk
 * @Date 2021/6/25 15:09
 * @Version 1.0
 */
public class ServerInvocationHandler implements InvocationHandler {

    private Object object;

    public ServerInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object invoke = method.invoke(object, args);

        return invoke;
    }
}
