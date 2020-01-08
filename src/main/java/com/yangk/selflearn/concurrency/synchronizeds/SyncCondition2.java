package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 两个线程访问的是两个对象的同步方法
 *  --无先后顺序,因为锁对象不同
 * @Author yangkun
 * @Date 2020/1/8
 * @Version 1.0
 */
public class SyncCondition2 implements Runnable {

    private static SyncCondition2 instance1 = new SyncCondition2();
    private static SyncCondition2 instance2 = new SyncCondition2();

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
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("运行结束");
    }
}
