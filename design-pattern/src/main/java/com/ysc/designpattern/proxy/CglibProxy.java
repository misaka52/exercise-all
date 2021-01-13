package com.ysc.designpattern.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yuanshancheng
 * @date 2021/1/7
 */
public class CglibProxy implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib-before");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("cglib-after");
        return result;
    }
}
