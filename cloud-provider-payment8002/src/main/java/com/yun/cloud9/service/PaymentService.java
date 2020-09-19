package com.yun.cloud9.service;

import com.yun.cloud9.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-17 10:46
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
