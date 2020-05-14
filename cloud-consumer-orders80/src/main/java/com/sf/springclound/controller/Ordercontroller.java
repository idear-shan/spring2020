package com.sf.springclound.controller;


import com.sf.springcloud.entitys.CommonResult;
import com.sf.springcloud.entitys.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Ordercontroller {

    private static final String Pay_url = "http://CLOUD-PAYMENT-SERVICE";//Eureka注册进去的服务名称
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/conmuser/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(Pay_url + "/payment/creat", payment, CommonResult.class);

    }

    @GetMapping("/conmuser/payment/query/{id}")
    public CommonResult<Payment> getquery(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(Pay_url + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            log.info(String.valueOf(forEntity.getHeaders()));
            return forEntity.getBody();//返回信息
        } else {
            return new CommonResult(444, "操作失败",id);
        }
    }

}
