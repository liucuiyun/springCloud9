package com.yun.cloud9.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-17 14:41
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced // 使用注解@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
