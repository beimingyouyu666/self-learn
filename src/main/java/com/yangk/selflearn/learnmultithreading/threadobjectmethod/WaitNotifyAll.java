package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 使用notifyAll方法
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class WaitNotifyAll implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        Thread thread1 = new Thread(waitNotifyAll);
        Thread thread2 = new Thread(waitNotifyAll);
        Thread thread3 = new Thread(waitNotifyAll);
        thread1.start();
        thread2.start();
        thread3.start();
        // 不休眠就无法保证执行顺序
        Thread.sleep(100);

        new Thread(() -> {
            synchronized (WaitNotifyAll.class) {
                WaitNotifyAll.class.notifyAll();
//                WaitNotifyAll.class.notify();
            }
        }).start();
        System.out.println("thread1" + thread1.getState());
        System.out.println("thread2" + thread2.getState());
        System.out.println("thread3" + thread3.getState());
    }

    @Override
    public void run() {
        try {
            synchronized (WaitNotifyAll.class) {
                System.out.println(Thread.currentThread().getName() + "启动");
                WaitNotifyAll.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束");
        }

    }
}
