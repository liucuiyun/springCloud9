package com.yun.cloud9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-18 10:36
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002Application.class, args);
    }
}
