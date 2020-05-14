package com.sf.springcloud.services;

import org.springframework.stereotype.Component;

@Component
public class Paymentfallbacl implements Paymenthystrixservices {
    @Override
    public String show(Integer id) {
        return "服务器出现异常。。。请重试";
    }

    @Override
    public String showout(Integer id) {
        return "服务器出现...请重试";
    }
}
