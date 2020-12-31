package com.ysc.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Configuration
@Profile("dev")
public class DevConfig implements EnvConfig {

    @Override
    public String getEnv() {
        return "dev";
    }
}