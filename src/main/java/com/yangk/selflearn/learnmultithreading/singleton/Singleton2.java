package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 饿汉式静态代码块，与第一种实质是一样的 -- 无法实现延迟加载，可能后续没用，造成资源浪费
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton2 {

    private static Singleton2 instance;

    static {
        instance = new Singleton2();
    }

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return instance;
    }
}
