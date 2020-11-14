package com.yun.cloud9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-24 15:10
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderFeignHystrix80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrix80Application.class, args);
    }
}
