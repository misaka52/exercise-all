package com.ysc.designpattern.template;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class Badminton extends Game {
    @Override
    void init() {
        System.out.println("准备球拍，找场地");
    }

    @Override
    void start() {
        System.out.println("打羽毛球");
    }
}
