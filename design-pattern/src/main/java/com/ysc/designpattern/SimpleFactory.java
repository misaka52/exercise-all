package com.ysc.designpattern;

/**
 * 简单工厂
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class SimpleFactory {
    /**
     * 根据类型来获取不同的对象
     * @param type
     * @return
     */
    public Object get(String type) {
        if ("int".equals(type)) {
            return 1;
        } else if ("string".equals(type)) {
            return new String("test");
        } else {
            return new Object();
        }
    }
}
