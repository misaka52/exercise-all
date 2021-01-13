package com.ysc.springboot.config;

import com.ysc.springboot.IConfig;
import com.ysc.springboot.mapper.ProductMapper;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
//@Configuration
@Service
@ToString
public class CommonConfig implements IConfig {
    @Value("${app.env}")
    private String appEnv;
    @Autowired
    private ProductMapper mapper;

    @Override
    public void print() {

    }
//    @Value("${prop.studentList}")
//    private List<String> studentList;
}