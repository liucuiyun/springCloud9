package com.yun.cloud9.controller;

import com.yun.cloud9.entities.CommonResult;
import com.yun.cloud9.entities.Payment;
import com.yun.cloud9.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-23 15:59
 */
@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        // openfeign-ribbon,客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeOut();
    }
}
