package com.ysc.springboot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuanshancheng
 * @date 2021/1/8
 */
@Component
@Getter
@RequiredArgsConstructor
public class ServiceA {
    @Autowired
    private ServiceB serviceB;
//    private final ServiceB serviceB;

    public String hello() {
        return "this is serviceA";
    }
}
