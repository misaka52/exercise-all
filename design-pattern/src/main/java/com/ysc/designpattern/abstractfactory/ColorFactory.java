package com.ysc.designpattern.abstractfactory;

import com.ysc.designpattern.factory.Shape;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Shape getShape(String type) {
        return null;
    }

    @Override
    public Color getColor(String type) {
        if ("red".equals(type)) {
            return new Red();
        } else if ("green".equals(type)) {
            return new Green();
        }
        return null;
    }
}
