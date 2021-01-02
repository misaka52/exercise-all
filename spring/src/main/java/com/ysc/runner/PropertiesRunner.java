package com.ysc.runner;

import com.ysc.configuration.JdbcSource;
import com.ysc.configuration.MysqlConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author yuanshancheng
 * @date 2020/12/26
 */
@Component
@Slf4j
public class PropertiesRunner implements CommandLineRunner {
    @Autowired
    private MysqlConfiguration mysqlConfiguration;
    @Autowired
    private JdbcSource jdbcSource;
    @Override
    public void run(String... args) throws Exception {
        log.info("mysqlConfig:{}", mysqlConfiguration);
        System.out.println(jdbcSource);
        getBeanFromContext();
    }


    public void getBeanFromContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcSource.class);
        JdbcSource bean = context.getBean(JdbcSource.class);
        bean.print();
    }
}
