package com.yun.cloud9.service.impl;

import com.yun.cloud9.dao.PaymentDao;
import com.yun.cloud9.entities.Payment;
import com.yun.cloud9.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-17 10:47
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
