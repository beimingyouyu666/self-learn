package com.yangk.selflearn.designpattern.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description 具体业务的动态代理
 * @Author yangkun
 * @Date 2019/10/18
 * @Version 1.0
 * @blame yangkun
 */
public class SubjectDynamicProxy extends DynamicProxy {

    public static <T> T newInstance(Subject subject) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);
        ClassLoader classLoader = subject.getClass().getClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, myInvocationHandler);
    }

}
