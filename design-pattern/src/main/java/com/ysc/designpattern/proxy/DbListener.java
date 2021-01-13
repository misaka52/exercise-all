package com.ysc.designpattern.proxy;

/**
 * @author yuanshancheng
 * @date 2021/1/6
 */
public class DbListener implements IListener {
    public void listen() {
        System.out.println(this.getClass().getName() + ":获取db变化");
    }
}
