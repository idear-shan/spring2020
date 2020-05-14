package com.sf.springcloud.services;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;



@Service
public class Payservices {


    public String showOK(Integer id) {

        return "线程池" + Thread.currentThread().getName() + "payok" + id;
    }


    //服务降级
    @HystrixCommand(fallbackMethod = "showout_hand", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })

    public String showout(Integer id) throws InterruptedException {

        return "线程池" + Thread.currentThread().getName() + "payout" + id + "耗时" + id;
    }

    public String showout_hand(Integer id) {

        return "当前系统忙" + Thread.currentThread().getName() + "/(ㄒoㄒ)/~~";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//开启短路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),//设置请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),//设置时间窗口期，毫秒单位
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"), //失败率达到多少时跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {

        if (id < 0) {
            throw new RuntimeException();
        } else {
            String serialNumber = IdUtil.simpleUUID();
            return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
        }

    }

    //
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，o(╥﹏╥)o id：" + id;
    }

}
