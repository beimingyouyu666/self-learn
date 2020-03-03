package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 饿汉式 -- 类加载的时候就会创建实例。无法实现延迟加载，可能后续没用，造成资源浪费
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
