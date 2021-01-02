package com.ysc.designpattern.singleton;

import java.io.Serializable;

/**
 * 懒汉式
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class LazySingleton implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private LazySingleton() {}
    private static volatile LazySingleton instance = null;

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    /**
                     * 通过new方法创建实例对象需要进行三步：1、申请内存空间；2、对象初始化；3、对象引用赋值
                     * 其中方法2 3不存在先后关系，可能产生重排序，导致返回了一个未初始化完成的对象，使用出现问题
                     */
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    private Object readResolve() {
        return instance;
    }

    @Override
    public Object clone() {
        return instance;
    }
}
