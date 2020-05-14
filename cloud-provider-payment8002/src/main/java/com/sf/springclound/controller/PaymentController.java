package com.sf.springclound.controller;


import com.sf.springcloud.entitys.CommonResult;
import com.sf.springcloud.entitys.Payment;
import com.sf.springclound.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/creat")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入成功" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(440, "插入失败！", null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult query(@PathVariable Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        if (null != paymentById) {
            return new CommonResult(200, "查询成功,serverPort" + serverPort, paymentById);
        } else {
            return new CommonResult(440, "查询失败！没有" + id, null);
        }

    }
}
