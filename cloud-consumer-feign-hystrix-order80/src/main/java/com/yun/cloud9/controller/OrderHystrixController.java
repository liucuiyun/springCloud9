package com.yun.cloud9.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yun.cloud9.service.PaymHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-24 15:15
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallBack")
public class OrderHystrixController {

    @Resource
    private PaymHystrixService paymHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        String result = paymHystrixService.paymentInfoOK(id);
        log.info(" >> consumer正常执行结果 ->：{}" + result);
        return result;

    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallBackHanlder", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        String result = paymHystrixService.paymentInfoTimeOut(id);
        log.info(" >> consumer超时执行结果 ->：{}" + result);
        /*int result = 10/0;
        return "result";*/
        return result;
    }
    public String paymentInfoTimeOutFallBackHanlder(Integer id) {
        return " >> 我是消费者80，对方支付系统繁忙请10秒钟之后再试或者自己运行出错，请检查自己，o(╥﹏╥)o ->：{}";
    }

    // 全局fallback
    public String paymentGlobalFallBack() {
        return " >> 全局服务降级公共方法 ->Global异常处理信息，请稍后再试：{}";
    }
}
