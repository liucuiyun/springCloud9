package com.yun.cloud9.controller;

import com.yun.cloud9.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-24 13:53
 */
@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoOK(id);
        log.info(" >> payment正常执行结果 ->：{}" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoTimeOut(id);
        log.info(" >> payment超时执行结果 ->：{}" + result);
        return result;
    }

     // 服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info(" >> 服务熔断 -> 执行结果：{}" + result);
        return result;
    }
}
