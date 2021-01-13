package com.ysc.springmvc.controller;

import com.ysc.springmvc.annotation.Controller;
import com.ysc.springmvc.annotation.RequestMapping;
import com.ysc.springmvc.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanshancheng
 * @date 2021/1/12
 */
@Controller
@RequestMapping("course")
public class QueryCourseController {
    @RequestMapping("query")
    @ResponseBody
    public Map<String, Object> query(String name, String age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return map;
    }

    @RequestMapping("add")
    @ResponseBody
    public String add() {
        return "add success";
    }
}
