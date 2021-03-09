package com.ysc.exercise.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yuanshancheng
 * @date 2021/1/24
 */
@ConfigurationProperties(prefix = "spring.wrap")
@Data
public class WrapConfiguration {
    private String prefix;
    private String suffix;
}
