package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 两个线程交替打印0~100的奇偶数，用wait/notify方法实现
 * 同样的run实现，进入方法就打印（main方法控制偶线程先开始）
 * 然后唤醒其他线程，自己睡眠
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class PrintOddEvenWaitNotify {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Impl(), "偶数").start();
        Thread.sleep(100);
        new Thread(new Impl(), "奇数").start();
    }

    static class Impl implements Runnable {

        @Override
        public void run() {
            synchronized (Impl.class) {
                while (count <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    try {
                        Impl.class.notify();
                        Impl.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
