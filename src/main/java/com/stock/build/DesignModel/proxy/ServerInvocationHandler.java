package com.stock.build.DesignModel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ======================
 *      代理对象角色
 * ======================
 * *****************************************
 * 代理对象内部含有目标对象的引用，
 * 从而可以在任何时候操作目标对象；
 * 代理对象提供一个与目标对象相同的接口，
 * 以便可以在任何时候替代目标对象
 * *****************************************
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
