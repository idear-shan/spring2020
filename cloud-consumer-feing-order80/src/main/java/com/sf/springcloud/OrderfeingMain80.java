package com.sf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients//开启feign
public class OrderfeingMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderfeingMain80.class, args);
    }
}
