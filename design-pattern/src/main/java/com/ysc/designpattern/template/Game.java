package com.ysc.designpattern.template;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public abstract class Game {
    abstract void init();
    abstract void start();

    public void play() {
        long start = System.currentTimeMillis();

        init();

        start();
        System.out.println("game play cost " + (System.currentTimeMillis() - start) + "ms");
    }
}
