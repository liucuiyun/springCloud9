package com.yun.cloud9.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-24 13:43
 */
@Service
public class PaymentHystrixService {

    // 服务降级
    public String paymentInfoOK(@PathVariable Integer id) {
        return " >> 正常模拟 ->线程池：{}" + Thread.currentThread().getName()
                + ", ->id: {}" + id + "OK!";
    }

    /**
     * 故意制造异常，模拟服务降级处理
     * 异常1.超时异常，设置业务能接受3秒处理，程序运行5秒，从而模拟超时异常。(注当设置超时时间和程序运行时间相等时也会出现超时异常)
     * 异常2.计算异常
     * 由于异常导致当前服务不可用了，做服务降级，兜底(服务降级)的方案由方法 paymentInfoTimeOutHandler 去处理
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallBackMethod", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfoTimeOut(@PathVariable Integer id) {
        int timeOut = 5;
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return " >> 超时模拟 ->线程池：{}" + Thread.currentThread().getName()
                + ", ->id: {}" + id + ", ->模拟成功 O(∩_∩)O 哈哈~TimeOut = {}" + timeOut;
        /*int calc = 10/0;
        return " >> 计算异常模拟 ->线程池：{}" + Thread.currentThread().getName() + ", ->id: {}" + id;*/
    }
    public String paymentInfoTimeOutFallBackMethod(Integer id) {
        return " >> 服务降级处理 ->当前线程池：{}" + Thread.currentThread().getName()
                + ", ->id: {}" + id + " o(╥﹏╥)o  服务超时致系统繁忙或运行报错请稍后再试";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数超过峰值，断路器会从关闭到开启
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException(" >> 参数错误 -> id不能为负数: {}");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " >> 调用成功 -> 流水号 = {}" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return " >> 请求参数错误 -> o(╥﹏╥)o 参数id不能为负数: {}" + id;
    }
}
