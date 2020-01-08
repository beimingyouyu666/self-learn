package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 两个线程同时访问一个对象的同步方法
 *  --有先后顺序,因为锁对象是同一个
 * @Author yangkun
 * @Date 2020/1/8
 * @Version 1.0
 */
public class SyncCondition1 implements Runnable {

    private static SyncCondition1 instance = new SyncCondition1();

    @Override
    public synchronized void run() {
        System.out.println("开始执行，线程名为："+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束执行，线程名为："+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("运行结束");
    }
}
