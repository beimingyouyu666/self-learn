package com.yangk.mystudy.autoconfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/9/18
 * @Version 1.0
 * @blame yangkun
 */
@Configuration
@ConditionalOnClass(SpringBootApplication.class)
public class AutoConfigTest {

//    @Bean
//    public AutoBean getAutoBean(){
//        return new AutoBean();
//    }
}
