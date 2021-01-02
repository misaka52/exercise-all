package com.ysc.designpattern.template;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class BasketBall extends Game {

    @Override
    void init() {
        System.out.println("准备篮球，找人");
    }

    @Override
    void start() {
        System.out.println("打篮球");
    }
}
