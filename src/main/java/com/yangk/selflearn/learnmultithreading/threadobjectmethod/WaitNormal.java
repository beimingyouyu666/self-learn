package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description wait方法的普通用法
 * @Author yangkun
 * @Date 2020/2/15
 * @Version 1.0
 */
public class WaitNormal {

    public static void main(String[] args) throws InterruptedException {
        Thread0 thread0 = new Thread0();
        Thread1 thread1 = new Thread1();
        thread0.start();
        Thread.sleep(50);
        thread1.start();
    }

    static class Thread0 extends Thread {

        @Override
        public void run() {
            synchronized (WaitNormal.class) {
                System.out.println(Thread.currentThread().getName() + "启动");
                try {
                    WaitNormal.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结束");
            }
        }
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (WaitNormal.class) {
                System.out.println(Thread.currentThread().getName() + "启动");
                WaitNormal.class.notify();
                System.out.println(Thread.currentThread().getName() + "结束");
            }
        }
    }
}
