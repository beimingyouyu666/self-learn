package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 只能释放调用者所对应的锁
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class ReleseOwnerMonitor {

    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("线程1获取resource1");
                synchronized (resource2) {
                    System.out.println("线程1获取resource2");
                    try {
                        System.out.println("线程1释放resource1");
                        resource1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2想获取锁");
            synchronized (resource1) {
                System.out.println("线程2获取resource1");
                synchronized (resource2) {
                    System.out.println("线程2获取resource2");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
