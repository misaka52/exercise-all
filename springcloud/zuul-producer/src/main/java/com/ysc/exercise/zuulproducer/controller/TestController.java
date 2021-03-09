package com.ysc.exercise.zuulproducer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanshancheng
 * @date 2021/1/30
 */
@RequestMapping("test")
@RestController
public class TestController {
    @GetMapping("index")
    public String index() {
        return "hello";
    }

    @GetMapping("index2")
    public String index2() {
        return "hello2";
    }
}
