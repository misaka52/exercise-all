package com.ysc.springboot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuanshancheng
 * @date 2021/1/8
 */
@Component
@Slf4j
@Getter
@RequiredArgsConstructor
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

//    private final ServiceA serviceA;

    public String hello() {
        return "this is serviceB";
    }
}
