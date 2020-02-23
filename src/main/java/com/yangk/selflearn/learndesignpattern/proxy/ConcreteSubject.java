package com.yangk.selflearn.learndesignpattern.proxy;

/**
 * @Description 具体的被代理类
 * @Author yangkun
 * @Date 2019/10/17
 * @Version 1.0
 * @blame yangkun
 */
public class ConcreteSubject implements Subject{
    @Override
    public void doSomething() {
        System.out.println("具体的被代理类实现方法");
    }
}
