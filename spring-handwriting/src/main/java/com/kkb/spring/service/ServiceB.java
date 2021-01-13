package com.kkb.spring.service;

/**
 * @author yuanshancheng
 * @date 2021/1/8
 */
public class ServiceB {

    private ServiceA serviceA;

    public void print() {
        System.out.println("this is serviceB");
    }

    public ServiceA getServiceA() {
        return serviceA;
    }

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
