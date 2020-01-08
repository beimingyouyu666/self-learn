package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 两个线程访问的是synchronized的静态方法
 *  --有先后顺序,因为锁对象是class对象
 * @Author yangkun
 * @Date 2020/1/8
 * @Version 1.0
 */
public class SyncCondition3 implements Runnable {

    private static SyncCondition3 instance1 = new SyncCondition3();
    private static SyncCondition3 instance2 = new SyncCondition3();

    @Override
    public void run() {
        method();
    }

    private static synchronized void method() {
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
