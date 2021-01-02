package com.ysc.designpattern.prototype;

import java.io.*;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class Prototype implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private SerializableObject key;

    public Object getKey() {
        return key;
    }

    public void setKey(SerializableObject key) {
        this.key = key;
    }

    /**
     * 浅拷贝，需要实现Cloneable接口
     * @return
     */
    public Prototype shallowClone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 深拷贝，类及属性都需要实现Serializable接口
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Prototype deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Prototype) ois.readObject();
    }
}

