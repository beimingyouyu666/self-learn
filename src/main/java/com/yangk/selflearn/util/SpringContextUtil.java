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

    /**
     * 上下文
     */
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
    }

    /**
     * @Description 获取上下文
     * @Author yangkun
     * @Date 2020/1/13
     * @Param []
     * @Return
     **/
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Description 设置上下文
     * @Author yangkun
     * @Date 2020/1/13
     * @Param [context]
     * @Return
     **/
    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * @Description 通过bean名称获取bean实例
     * @Author yangkun
     * @Date 2020/1/13
     * @Param [name]
     * @Return
     **/
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * @Description 通过bean的类型获取bean实例
     * @Author yangkun
     * @Date 2020/1/13
     * @Param [requiredType]
     * @Return
     **/
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * @Description 获取当前环境
     * @Author yangkun
     * @Date 2020/1/13
     * @Param []
     * @Return
     **/
    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
