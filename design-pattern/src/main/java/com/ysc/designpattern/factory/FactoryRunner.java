package com.ysc.designpattern.factory;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class FactoryRunner {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape1 = factory.getShape("triangle");
        shape1.draw();

        Shape shape2 = factory.getShape("rectangle");
        shape2.draw();
    }
}
