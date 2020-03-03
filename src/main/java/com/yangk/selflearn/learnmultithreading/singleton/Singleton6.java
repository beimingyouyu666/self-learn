package com.yangk.selflearn.learnmultithreading.singleton;

/**
 * @Description 双重检测（推荐使用） -- 线程安全，实现了延迟加载
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class Singleton6 {

    // 使用volatile，防止内存重排序
    private volatile static Singleton6 instance;

    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    // 使用volatile，防止内存重排序
                    // 因为 new Singleton6()分为三个步骤：1、创建一个空对象 2、调用构造方法 3、将实例对象指向instance
                    // 可能会造成内存重排，1-3-2，就会导致instance不为null，此时对象内的属性还未赋值，
                    // 其他线程拿到instance使用属性就可能发生空指针异常
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
