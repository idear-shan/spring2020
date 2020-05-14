package springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sf.springcloud.entitys.CommonResult;
import com.sf.springcloud.entitys.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloud.myhandler.CustomerBlockHandler;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用",null);
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl",blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "handlerException")
    public CommonResult byUrl(){

        return new CommonResult(200,"按url限流测试OK", new Payment(2020L, "serial001"));
    }

    //CustomerBlockHandler
    @GetMapping("/rateLimit/CustomerBlockHandler")
    @SentinelResource(value = "CustomerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult CustomerBlockHandler() throws InterruptedException {
        Thread.sleep(300);
        log.info("66666");
        return new CommonResult(200,"按客户自定义限流测试OK0000ccc", new Payment(2020L, "serial003"));
    }

}
