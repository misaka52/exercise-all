package com.ysc.springboot.config;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.models.role.RedisInstance;
import io.lettuce.core.models.role.RedisNodeDescription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yuanshancheng
 * @date 2021/5/6
 */
@Configuration
@Component
@Slf4j
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380)
                .sentinel("127.0.0.1", 26381);
        // 方法一：默认的配置，但存在问题，每次都选择第一个连接
//        LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder()
//                .readFrom(ReadFrom.REPLICA_PREFERRED).build();
        // 方法二：自定义，优先从slave节点中获取，代码后续从list随机取值以达到负载均衡
        LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder().readFrom(new ReadFrom() {
            @Override
            public List<RedisNodeDescription> select(Nodes nodes) {
                List<RedisNodeDescription> slaveNodes = nodes.getNodes().stream()
                        .filter(node -> node.getRole() == RedisInstance.Role.SLAVE)
                        .collect(Collectors.toList());
                return slaveNodes;
//                return Collections.singletonList(nodes.getNodes());
            }
        }).commandTimeout(Duration.ofMillis(10000))
                .build();
        return new LettuceConnectionFactory(redisSentinelConfiguration, lettuceClientConfiguration);
    }
}
