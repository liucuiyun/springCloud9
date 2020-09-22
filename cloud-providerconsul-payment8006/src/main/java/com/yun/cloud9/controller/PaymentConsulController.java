package com.yun.cloud9.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-21 15:41
 */
@RestController
public class PaymentConsulController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentConsul() {
        return " >> springCloud with consul ->serverPort: {}" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
