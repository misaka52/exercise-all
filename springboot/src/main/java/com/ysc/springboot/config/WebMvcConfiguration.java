package com.ysc.springboot.config;

import com.ysc.springboot.interceptor.LogInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private final LogInterceptor logInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/test/**")
                .addPathPatterns("/**")
                .excludePathPatterns("/test/hello/**");
    }
}
