package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 懒汉式 -- 实现了延迟加载，但是线程不安全
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {
    }

    public Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }


}
