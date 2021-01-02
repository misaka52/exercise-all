package com.ysc.designpattern.singleton;

/**
 * 饿汉式
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class HungerSingleton {
    private HungerSingleton() {}
    private static HungerSingleton instance = new HungerSingleton();

    public static HungerSingleton getInstance() {
        return instance;
    }
}
