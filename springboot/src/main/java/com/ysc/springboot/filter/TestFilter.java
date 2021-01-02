package com.ysc.springboot.filter;

import com.ysc.springboot.config.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.awt.*;
import java.io.IOException;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
@WebFilter("/*")
@Slf4j
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("进入过滤方法");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
