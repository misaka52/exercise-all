package com.ysc.configuration;

import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yuanshancheng
 * @date 2020/12/26
 */
@Configuration
@Import({MysqlConfiguration.class})
@ToString
public class JdbcSource {
    private MysqlConfiguration mysqlConfiguration;

    public JdbcSource(MysqlConfiguration mysqlConfiguration) {
        this.mysqlConfiguration = mysqlConfiguration;
    }

    public void print() {
        System.out.println(this);
    }
}
