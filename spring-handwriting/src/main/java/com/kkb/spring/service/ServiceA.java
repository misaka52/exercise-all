package com.kkb.spring.service;

/**
 * @author yuanshancheng
 * @date 2021/1/8
 */
public class ServiceA {
    private ServiceB serviceB;
    public void print() {
        System.out.println("this is serviceA");
    }

    public ServiceB getServiceB() {
        return serviceB;
    }

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

//    public ServiceA(ServiceB serviceB) {
//        this.serviceB = serviceB;
//    }
}
