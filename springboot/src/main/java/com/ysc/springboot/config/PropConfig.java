package com.ysc.springboot.config;

import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Component
@ConfigurationProperties("prop")
@ToString
@Setter
public class PropConfig {
    private List<String> studentList;
}