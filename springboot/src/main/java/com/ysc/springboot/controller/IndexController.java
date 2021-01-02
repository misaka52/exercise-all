package com.ysc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("hello")
    public String index(Model model) {
        model.addAttribute("name", "zs");
        model.addAttribute("age", 19);
        return "home/hello";
    }
}
