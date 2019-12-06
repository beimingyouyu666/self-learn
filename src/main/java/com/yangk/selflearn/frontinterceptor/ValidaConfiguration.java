package com.yangk.selflearn.frontinterceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 配置类，配置拦截器
 * @Author yangkun
 * @Date 2019/12/2
 * @Version 1.0
 * @blame yangkun
 */
@Configuration
public class ValidaConfiguration implements WebMvcConfigurer {

	
	@Bean
    public ValidaInterceptor myInterceptor(){
        return new ValidaInterceptor();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加拦截的接口
		registry.addInterceptor(myInterceptor())
		.addPathPatterns("/hello")
		.addPathPatterns("/hehe");
	}

}
