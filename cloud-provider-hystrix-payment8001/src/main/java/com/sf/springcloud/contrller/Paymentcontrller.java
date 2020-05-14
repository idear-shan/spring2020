package com.sf.springcloud.contrller;

import cn.hutool.core.util.IdUtil;
import com.sf.springcloud.services.Payservices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Paymentcontrller {
    @Autowired
    private Payservices payservices;
    @Value("${server.port}")
    private String serverport;

    @RequestMapping("/payment/hystrix/{id}")
    public String show(@PathVariable("id") Integer id) {
        String res = payservices.showOK(id);
        log.info(res);
        return res;

    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String showout(@PathVariable("id") Integer id) throws InterruptedException {
        String res = payservices.showout(id);

        log.info(res);
        return res;

    }

    @GetMapping("/payment/hystrix/showfabaeak/{id}")
    public String showfabaeak(@PathVariable("id") Integer id) throws InterruptedException {
        String res = payservices.paymentCircuitBreaker(id);

        log.info(res);
        return res;

    }

}
