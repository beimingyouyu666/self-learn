package com.yangk.selflearn.learnmultithreading.multithreadsafe;

/**
 * @Description 多线程安全问题1--a++
 * <p>
 * 两个线程同时对a进行操作，不能得到预期的20000
 * @Author yangkun
 * @Date 2020/2/21
 * @Version 1.0
 */
public class MultiThreadError1 implements Runnable {

    private static int a;

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError1 multiThreadError1 = new MultiThreadError1();
        Thread thread1 = new Thread(multiThreadError1);
        Thread thread2 = new Thread(multiThreadError1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("a:" + a);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
        }
    }
}
