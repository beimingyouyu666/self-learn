package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 两个线程同时访问同一个对象的不同的普通同步方法
 * --默认都是使用this对象作为锁，无先后顺序
 * @Author yangkun
 * @Date 2020/1/8
 * @Version 1.0
 */
public class SyncCondition5 implements Runnable {

    private static SyncCondition5 instance1 = new SyncCondition5();

    @Override
    public void run() {
        if ("Thread-0".equals(Thread.currentThread().getName())) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("同步方法开始执行，线程名为：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("同步方法结束执行，线程名为：" + Thread.currentThread().getName());
    }

    private synchronized void method2() {
        System.out.println("同步方法2开始执行，线程名为：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("同步方法2结束执行，线程名为：" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("运行结束");
    }
}
