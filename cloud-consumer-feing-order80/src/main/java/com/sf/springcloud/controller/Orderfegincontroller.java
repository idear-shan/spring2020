package com.sf.springcloud.controller;

import com.sf.springcloud.entitys.CommonResult;
import com.sf.springcloud.entitys.Payment;
import com.sf.springcloud.PaymentFeginservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Orderfegincontroller {
    @Resource
    private PaymentFeginservice paymentFeginservice;

    @RequestMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeginservice.getPaymentById(id);
    }


}
