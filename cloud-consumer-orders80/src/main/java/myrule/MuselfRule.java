package myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * ribbon 规则类
 *
 */
@Configuration
public class MuselfRule {
    @Bean
    public IRule myrule() {
        return new RandomRule();//定义为随机
    }


}
