package com.sf.springcloud.contrller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.sf.springcloud.services.Paymenthystrixservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Oedercontrller {
    @Autowired
    private Paymenthystrixservices paymenthystrixservices;

    @RequestMapping("consummer/payment/hystrix/{id}")
    //走默认服务降级方案
    @HystrixCommand
    public String ordershow(@PathVariable("id") Integer id) {
        return paymenthystrixservices.show(id);
    }


    //自定义服务策略，在方法上加自定义的不会走全局的默认服务降级
    @HystrixCommand(fallbackMethod = "showout_hand", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })

    @GetMapping("consummer/payment/hystrix/timeout/{id}")
    public String ordershowout(@PathVariable("id") Integer id)
    {
        return paymenthystrixservices.showout(id);
    }

    public String showout_hand(Integer id) {
        return "支付系统当前系统忙" + Thread.currentThread().getName() + "/(ㄒoㄒ)/~~";
    }

}
