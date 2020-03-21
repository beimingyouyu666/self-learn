package com.yangk.selflearn.learnmultithreading.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description 死锁演示
 * @Author yangkun
 * @Date 2020/3/15
 * @Version 1.0
 */
public class ThreadMXBeanDetection implements Runnable{

    private int flag = 0;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection mustDeadlock1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection mustDeadlock2 = new ThreadMXBeanDetection();
        mustDeadlock1.flag = 0;
        mustDeadlock2.flag = 1;
        Thread thread1 = new Thread(mustDeadlock1, "thread1");
        Thread thread2 = new Thread(mustDeadlock2, "thread2");
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("发现死锁" + threadInfo.getThreadName());
            }
        }
    }

    @Override
    public void run() {
        if (flag == 0) {
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName()+"获取到lock1");
                    Thread.sleep(500);
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName()+"获取到lock2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (flag == 1) {
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName()+"获取到lock2");
                    Thread.sleep(500);
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName()+"获取到lock1");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
