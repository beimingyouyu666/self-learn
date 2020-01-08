package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 同步代码块演示
 * @Author yangkun
 * @Date 2020/1/6
 * @Version 1.0
 */
public class SyncCodeBlock2 implements Runnable{

    private static SyncCodeBlock2 syncCodeBlock2  = new SyncCodeBlock2();

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(syncCodeBlock2);
        Thread thread2 = new Thread(syncCodeBlock2);
        thread1.start();
        thread2.start();
        // 使用while,为了让前面的线程执行完再执行打印方法
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("执行结束");
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("线程开始，当前线程："+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束，当前线程："+Thread.currentThread().getName());
        }
    }
}
