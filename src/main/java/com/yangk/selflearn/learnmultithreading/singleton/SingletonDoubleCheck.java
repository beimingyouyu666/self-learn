package com.yangk.selflearn.learnmultithreading.singleton;

import java.io.Serializable;

/**
 * @Description 为了演示双重检测被反序列化破坏
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class SingletonDoubleCheck implements Serializable {

    private volatile static SingletonDoubleCheck instance;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }

    /**
     * @Description 防止反序列化破坏单例
     * @Author yangkun
     * @Date 2020/2/28
     * @Param []
     * @Return
     **/
    private Object readResolve() {
        return instance;
    }

}
