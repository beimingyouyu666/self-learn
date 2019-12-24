package com.yangk.selflearn.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description spring容器工具类，获取applicationContext，手动获取bean
 * @Author yangkun
 * @Date 2019/12/24
 * @Version 1.0
 * @blame yangkun
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
