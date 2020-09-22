package com.yun.cloud9.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-21 11:05
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/zk")
    public String paymentZK() {
        return " >> springCloud with zookeeper ->serverPort: {}" + serverPort + UUID.randomUUID().toString();
    }
}
