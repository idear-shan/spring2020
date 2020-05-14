package com.sf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//该注解使用于consul作为作为注册中心时得注册服务
public class OrderconsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderconsulMain80.class, args);

    }
}
