package com.ysc.configuration;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yuanshancheng
 * @date 2020/12/26
 */
@Configuration
// .yml文件不生效
@PropertySource("classpath:datasource.properties")
@ToString
public class MysqlConfiguration {
    @Value("${mysql.username}")
    private String userName;
    @Value("${mysql.password}")
    private String password;
}
