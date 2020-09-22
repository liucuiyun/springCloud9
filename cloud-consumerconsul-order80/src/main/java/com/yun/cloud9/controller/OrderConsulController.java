package com.yun.cloud9.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-21 16:10
 */
@RestController
public class OrderConsulController {

    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String consumerConsulInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }
}
