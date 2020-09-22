package com.yun.cloud9.controller;

import com.yun.cloud9.entities.CommonResult;
import com.yun.cloud9.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-17 14:42
 */
@RestController
@Slf4j
public class OrderController {

//    private final static String PAYMENT_URL = "http://localhost:8001"; // 单机版
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; // 集群版

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 返回对象为响应体中数据转化成的对象，基本上可以理解为Json
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/discovery80")
    public Object discovery() {
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

    /**
     * 返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头，响应体，响应状态等
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult(444, "查询操作失败！");
        }
    }

    /**
     * 新建
     * @param payment
     * @return
     */
    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> createPayment(Payment payment) {
//        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class).getBody();
    }
}
