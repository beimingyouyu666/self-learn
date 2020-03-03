package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 懒汉式 -- 线程不安全
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5() {
    }

    public Singleton5 getInstance() {
        if (instance == null) {
            // thread1和thread2可以同时到这里，然后依次创建对象
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }


}
