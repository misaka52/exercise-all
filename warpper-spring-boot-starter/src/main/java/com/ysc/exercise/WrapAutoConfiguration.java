package com.ysc.exercise;

import com.ysc.exercise.service.WrapConfiguration;
import com.ysc.exercise.service.WrapService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanshancheng
 * @date 2021/1/24
 */
@Configuration
@EnableConfigurationProperties({WrapConfiguration.class})
@ConditionalOnClass(WrapService.class)
public class WrapAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "spring.wrap.enable", matchIfMissing = true, havingValue = "true")
    public WrapService registerWrap(WrapConfiguration wrapConfiguration) {
        return new WrapService(wrapConfiguration.getPrefix(), wrapConfiguration.getSuffix());
    }

    /**
     * 默认注入
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public WrapService registerWrap2() {
        return new WrapService("", "");
    }

}
