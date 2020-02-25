package com.yangk.selflearn.learndesignpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description 动态代理类，创建代理对象
 * @Author yangkun
 * @Date 2019/10/17
 * @Version 1.0
 * @blame yangkun
 */
public class DynamicProxy {

    public static <T> T newInstance(ClassLoader classLoader, Class<?>[] classes, InvocationHandler handler) {

        return (T) Proxy.newProxyInstance(classLoader, classes, handler);
    }

}
