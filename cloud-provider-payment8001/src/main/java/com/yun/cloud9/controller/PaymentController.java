package com.yun.cloud9.controller;

import com.alibaba.fastjson.JSONObject;
import com.yun.cloud9.entities.CommonResult;
import com.yun.cloud9.entities.Payment;
import com.yun.cloud9.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-17 11:06
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info(" >> 插入结果 ->result：{}，serverPort: {}", result, serverPort);
        if (result > 0) {
            return new CommonResult(200, "插入数据到数据库成功，serverPort: {}" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据到数据库失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info(" >> 8001查询结果 ->result：{}，serverPort: {}", JSONObject.toJSONString(payment), serverPort);
        if (payment != null) {
            return new CommonResult(200, "查询成功，serverPort: {}" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败，主键id:{}" + id, null);
        }
    }

    @GetMapping("/payment/discovery8001")
    public Object discovery() {
        /**
         * 对于注册进eureka里面的微服务payment，可以通过服务发现来获得该服务的信息
         * 那么（payment微服务）怎么暴露给对方我们自身的（调用者order）这些（payment微）服务信息呢？
         * 答，可以通过服务发现来获得该服务的信息
         */
        /**
         * 获取服务列表信息（服务清单列表）（获取eureka上有哪些服务）
         * 获取在eureka server注册中心注册过的，登录好的服务有哪些
         * 说白了就是在Application这里微服务名称有哪些（哪几个）
         */
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(" >> 发现服务 ->有：{}", service);
        }
        /**
         * 获取一个指定的微服务下面的全部各种实例
         * 通过微服务名称进一步获取微服务相关信息，即获取具体的实例信息，包括实例的ip，port等
         * CLOUD-PAYMENT-SERVICE就是对外暴露的微服务名称
         *
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            // instance就是该服务的一个具体的实例
            log.info(" >> 服务的实例信息 ->ServiceId: {}, InstanceId: {}，Host：{}，Port：{}，Uri：{}",
                    instance.getServiceId(), instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        // 获取相应的服务信息
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return "本次调用服务端口：{}" + serverPort;
    }

    /**
     * 服务提供方故意写暂停程序，供消费者 模拟Feign超时控制调用
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        try {
            // 暂停几秒钟线程
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
