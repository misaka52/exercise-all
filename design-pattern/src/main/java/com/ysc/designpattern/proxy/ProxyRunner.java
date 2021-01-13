package com.ysc.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.awt.*;
import java.lang.reflect.Proxy;

/**
 * @author yuanshancheng
 * @date 2021/1/6
 */
public class ProxyRunner {
    public static void main(String[] args) {
//        jdkDynamicProxy();
        cglibDynamicProxy();
    }

    static void jdkDynamicProxy() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IListener origin = new DbListener();
        System.out.println("origin:" + origin);
        origin.listen();
        IListener proxyObject = (IListener) Proxy.newProxyInstance(IListener.class.getClassLoader(),
                new Class[]{IListener.class},
                new MyInvocationHandler(origin));
        System.out.println("proxyObject:" + proxyObject);
        proxyObject.listen();
    }

    static void cglibDynamicProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DbListener.class);
        enhancer.setCallback(new CglibProxy());

        DbListener listener = (DbListener) enhancer.create();
        listener.listen();
    }
}
