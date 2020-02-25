package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 在join期间被中断的情况
 * thread1调用join方法，其实是main线程在等待子线程结束，所以中断的是main线程。
 * main线程中断后，子线程还会执行完毕，这就有问题，
 * 所以在捕获main线程中断异常时，要将中断标志设置给子线程:catch中加上thread1.interrupt();
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class JoinInterrupted {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println("子线程苏醒");

            } catch (InterruptedException e) {
                System.out.println("子线程被中断");
                e.printStackTrace();
            }

        });
        thread1.start();
        System.out.println("等待子线程执行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("main线程被中断");
            thread1.interrupt();
            e.printStackTrace();
        }
        System.out.println("子线程执行完毕");
    }

}
