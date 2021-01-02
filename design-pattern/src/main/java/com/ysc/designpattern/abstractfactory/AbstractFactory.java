package com.ysc.designpattern.abstractfactory;

import com.ysc.designpattern.factory.Shape;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public abstract class AbstractFactory {
    public abstract Shape getShape(String type);
    public abstract Color getColor(String type);
}
