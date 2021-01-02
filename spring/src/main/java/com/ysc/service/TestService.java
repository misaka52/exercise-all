package com.ysc.service;

import org.springframework.stereotype.Service;

/**
 * @author yuanshancheng
 * @date 2020/12/29
 */
@Service
public class TestService {
    public void fun() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
