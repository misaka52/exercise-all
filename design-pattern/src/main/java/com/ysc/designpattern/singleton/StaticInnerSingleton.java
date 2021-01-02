package com.ysc.designpattern.singleton;

/**
 * @author yuanshancheng
 * @date 2021/1/2
 */
public class StaticInnerSingleton {
    private StaticInnerSingleton() {
        System.out.println("StaticInnerSingleton对象 初始化");
    }
    private static class Inner {
        public static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return Inner.instance;
    }
}
