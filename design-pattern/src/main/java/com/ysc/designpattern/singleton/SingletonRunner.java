package com.ysc.designpattern.singleton;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author yuanshancheng
 * @date 2021/1/2
 */
public class SingletonRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        LazySingleton instance = LazySingleton.getInstance();
//        System.out.println(instance);

//        // 序列化反序列化
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(bos);
//        oos.writeObject(instance);
//
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        LazySingleton instance2 = (LazySingleton) ois.readObject();
//        System.out.println(instance2);
//
//        // 克隆
//        System.out.println(instance.clone());
//        innerTest();

        invoker();
        invoker();
    }

    static void innerTest() {
        System.out.println("初始化");
        System.out.println(StaticInnerSingleton.getInstance());
    }

    static void invoker() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class clazz = Class.forName(EnumSingleton.class.getName());
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());
    }
}
