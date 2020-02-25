package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 使用线程作为锁对象并调用wait方法
 * 使用thread1对象作为锁对象并调用wait方法，thread1.start()执行后，默认调用了notify（），thread也就自动结束了
 * <p>
 * 如果使用ThreadAsMonitor.class作为锁对象就没问题,thread1.start()执行后,thread还是会在等待中
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class ThreadAsMonitor {

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread thread = new Thread(() -> {
            synchronized (thread1) {
                System.out.println("匿名线程启动");
                try {
                    thread1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("匿名线程结束");

        });
        thread.start();
        System.out.println("thread:" + thread.getState());
        Thread.sleep(1000);
        System.out.println("线程thread1启动");
        thread1.start();
    }

    static class Thread1 extends Thread {

    }
}
