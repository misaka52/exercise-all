package com.ysc.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yuanshancheng
 * @date 2021/1/6
 */
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 代理对象
     */
    private Object target;
    public MyInvocationHandler(Object object) {
        this.target = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理--start");
        Object result = method.invoke(target, args);
        System.out.println("jdk动态代理--end");
        return result;
    }
}
