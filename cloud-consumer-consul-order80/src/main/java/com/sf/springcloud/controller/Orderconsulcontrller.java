package com.sf.springcloud.controller;

import com.sf.springcloud.entitys.CommonResult;
import com.sf.springcloud.entitys.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class Orderconsulcontrller {
    @Autowired
    private RestTemplate restTemplate;
    private static final String Pay_url = "http://consul-provider-payment";//consul注册进去的服务名称

    @RequestMapping(value = "/consumer/consul")
    public String paymentzk() {
        return restTemplate.getForObject(Pay_url + "/payment/consul", String.class);
    }
}
