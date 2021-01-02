package com.ysc.designpattern.abstractfactory;

import com.ysc.designpattern.factory.RectangleShape;
import com.ysc.designpattern.factory.Shape;
import com.ysc.designpattern.factory.TriangleShape;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String type) {
        if ("triangle".equals(type)) {
            return new TriangleShape();
        } else if ("rectangle".equals(type)) {
            return new RectangleShape();
        } else {
            return null;
        }
    }

    @Override
    public Color getColor(String type) {
        return null;
    }
}
