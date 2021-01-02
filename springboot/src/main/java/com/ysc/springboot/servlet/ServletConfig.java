package com.ysc.springboot.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<TestServlet> getServletBean() {
        TestServlet servlet = new TestServlet();
        return new ServletRegistrationBean<>(servlet, "/servlet2");
    }
}
