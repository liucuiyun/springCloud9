package com.yun.cloud9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-21 16:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsul80Application {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsul80Application.class, args);
    }
}
