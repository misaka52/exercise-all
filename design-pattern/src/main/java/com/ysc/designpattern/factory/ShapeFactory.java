package com.ysc.designpattern.factory;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class ShapeFactory {
    public Shape getShape(String type) {
        if ("triangle".equals(type)) {
            return new TriangleShape();
        } else if ("rectangle".equals(type)) {
            return new RectangleShape();
        } else {
            return null;
        }
    }
}
