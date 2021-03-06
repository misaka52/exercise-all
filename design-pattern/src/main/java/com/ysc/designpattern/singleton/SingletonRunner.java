package com.ysc.designpattern.singleton;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author yuanshancheng
 * @date 2021/1/2
 */
public class SingletonRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
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
//        invoker();
    }

    static void innerTest() {
        System.out.println("初始化");
        System.out.println(StaticInnerSingleton.getInstance());
    }

    static void invoker() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Class clazz = Class.forName(EnumSingleton.class.getName());
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());
    }
}
