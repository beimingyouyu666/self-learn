package com.yangk.selflearn.learnmultithreading.threadstop;

/**
 * @Description 关于interrupt几个方法的使用
 * <p>
 * 1、boolean isInterrupted 返回线程中断标志，不会清除中断状态
 * <p>
 * 2、static boolean interrupted() 返回线程中断标志，并会清除中断状态，
 * 这个方法的作用对象是当前运行的线程，与调用者无关
 * @Author yangkun
 * @Date 2020/2/14
 * @Version 1.0
 */
public class InterruptMethodDemo implements Runnable {
    @Override
    public void run() {
        for (; ; ) {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptMethodDemo());
        thread.start();
        thread.interrupt();
        // thread是中断的 打印true，不会清除中断状态
        System.out.println("线程状态为：" + thread.isInterrupted());
        // 这里是查看main线程的中断状态 打印false，会清除中断状态，还是为false
        System.out.println("线程状态为：" + Thread.interrupted());
        // 这里是查看main线程的中断状态 打印false
        System.out.println("线程状态为：" + Thread.interrupted());
        // thread是中断的 打印true，不会清除中断状态
        System.out.println("线程状态为：" + thread.isInterrupted());
        System.out.println(thread.getState());
        System.out.println(Thread.currentThread().getState());
        // 要等thread的方法运行完才会回到main线程
        thread.join();
        System.out.println("结束");

    }
}
