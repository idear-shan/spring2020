package com.sf.springcloud;

import com.sf.springcloud.entitys.CommonResult;
import com.sf.springcloud.entitys.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeginservice {

    @RequestMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


}
