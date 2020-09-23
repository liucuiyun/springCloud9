package com.yun.cloud9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-23 15:30
 */
@SpringBootApplication
@EnableFeignClients
public class OrderOpenFeign80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeign80Application.class, args);
    }
}
