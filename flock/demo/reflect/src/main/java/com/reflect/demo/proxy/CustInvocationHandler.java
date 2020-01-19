package com.reflect.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 14:14
 */
public class CustInvocationHandler implements InvocationHandler {

    private Object obj = null;

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object temp = method.invoke(this.obj, args);
        return temp;
    }
}
