package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

import java.util.concurrent.TimeUnit;

/**
 * @Description sleep在阻塞状态可以响应中断
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class SleepInterrupted implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        System.out.println("线程启动");
        try {
            // 使用TimeUnit.SECONDS.sleep来替代Thread.sleep，代码更清晰，
            // 如果传入负数不会抛出异常
//            Thread.sleep(3000);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("线程被中断");
            e.printStackTrace();
        }
    }
}
