package com.yun.cloud9.service;

import org.springframework.stereotype.Component;

/**
 * @author yun
 * @version 1.0
 * @date 2020-11-6 9:58
 */
@Component
public class PaymentFallbackService implements PaymHystrixService {
    @Override
    public String paymentInfoOK(Integer id) {
        return " >> PaymentFallbackService -> paymentInfoOK = {}OK! - o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return " >> PaymentFallbackService -> paymentInfoTimeOut = {}TIMEOUT! - o(╥﹏╥)o";
    }
}
