package com.yangk.mystudy.designpattern.proxy;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/10/17
 * @Version 1.0
 * @blame yangkun
 */
public class Client {

    public static void main(String[] args) {
//        Subject subject = new ConcreteSubject();
//        Subject proxy = DynamicProxy.newInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new MyInvocationHandler(subject));
//        proxy.doSomething();

        Subject subject = new ConcreteSubject();
        Subject proxy = SubjectDynamicProxy.newInstance(subject);
        proxy.doSomething();

    }

}
