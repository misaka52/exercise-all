package com.ysc.springboot.config;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Configuration
@ToString
public class CommonConfig {
    @Value("${app.env}")
    private String appEnv;
//    @Value("${prop.studentList}")
//    private List<String> studentList;
}