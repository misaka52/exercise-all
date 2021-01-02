package com.ysc.designpattern.prototype;

import java.io.IOException;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class PrototypeRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Prototype prototype = new Prototype();
        prototype.setKey(new SerializableObject());
        System.out.println(prototype.getKey());

        System.out.println(prototype.shallowClone().getKey());

        System.out.println(prototype.deepClone().getKey());
    }
}
