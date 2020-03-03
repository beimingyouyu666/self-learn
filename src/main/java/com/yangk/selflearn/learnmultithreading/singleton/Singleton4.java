package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 懒汉式 -- 实现了延迟加载，线程安全，但是并发效率太低
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {
    }

    public synchronized Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }


}
