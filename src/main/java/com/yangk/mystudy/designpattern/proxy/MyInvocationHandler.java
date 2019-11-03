package com.yangk.mystudy.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description 自己实现的动态代理处理类
 *  将被代理类通过构造器注入，反射执行方法
 * @Author yangkun
 * @Date 2019/10/17
 * @Version 1.0
 * @blame yangkun
 */
public class MyInvocationHandler implements InvocationHandler {

    private Subject target;

    public MyInvocationHandler(Subject target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据判断条件，是否执行前置通知
        if (true){
            new PreAdvice().advice();
        }
        Object invoke = method.invoke(target, args);
        System.out.println("后置通知");
        return invoke;

    }
}
