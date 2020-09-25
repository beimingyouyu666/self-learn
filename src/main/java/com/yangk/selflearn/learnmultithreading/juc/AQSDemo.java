package com.yangk.selflearn.learnmultithreading.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description aqs的demo
 * @Author yangkun
 * @Date 2020/9/24
 * @Version 1.0
 * @blame yangkun
 */
public class AQSDemo {

    public static void main(String[] args) {

        new Semaphore(1);
        new CountDownLatch(2);
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
