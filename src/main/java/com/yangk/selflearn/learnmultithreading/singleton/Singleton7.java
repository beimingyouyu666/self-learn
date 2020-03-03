package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 静态内部类（推荐使用） -- 线程安全，实现了延迟加载，代码有点麻烦
 *      内部类只有在使用到的时候才会进行加载
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton7 {

    private Singleton7() {
    }

    private static class Singleton7Inner {
        private static Singleton7 instance = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return Singleton7Inner.instance;
    }
}
