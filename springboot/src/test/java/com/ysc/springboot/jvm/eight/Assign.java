package com.ysc.springboot.jvm.eight;

/**
 * @author yuanshancheng
 * @date 2021/2/27
 */
public class Assign {
    public static void main(String[] args) {
        dynamicAssign();
    }

    static void dynamicAssign() {
        Father son = new Son();
        System.out.println(son.money);
    }

    static class Father {
        private int money = 1;
        public Father() {
            money = 2;
            showMoney();
        }

        public void showMoney() {
            System.out.println("I'm father, my money is " + money);
        }
    }
    static class Son extends Father {
        private int money = 3;
        public Son() {
            money = 4;
            showMoney();
        }

        public void showMoney() {
            System.out.println("I'm son, my money is " + money);
        }
    }

    static void staticAssign() {
        Human man = new Man();
        Human woman = new Woman();
        Assign assign = new Assign();
        assign.sayHello(man);
        assign.sayHello(woman);
        assign.sayHello((Man)man);
        assign.sayHello((Woman) woman);
    }

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("hello, human");
    }
    public void sayHello(Man human) {
        System.out.println("hello, Man");
    }
    public void sayHello(Woman human) {
        System.out.println("hello, woman");
    }
}
