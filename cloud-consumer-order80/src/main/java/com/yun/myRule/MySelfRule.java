package com.yun.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-23 9:22
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 负载均衡策略定义为随机
        return new RandomRule();
    }
}
