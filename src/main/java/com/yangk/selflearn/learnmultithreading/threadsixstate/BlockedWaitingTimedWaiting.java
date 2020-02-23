package com.yangk.selflearn.learnmultithreading.threadsixstate;

/**
 * @Description 演示线程状态： Blocked、 Waiting、 Timed_Waiting
 * @Author yangkun
 * @Date 2020/2/15
 * @Version 1.0
 */
public class BlockedWaitingTimedWaiting {

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(10);
        thread2.start();
        Thread.sleep(10);
        // thread2此时没获取到锁，为blocked
        System.out.println("thread2" + thread2.getState());
        // thread1此时执行了sleep方法，为timed_waiting
        System.out.println("thread1" + thread1.getState());
        Thread.sleep(1000);
        // thread1此时执行到了wait方法，为waiting
        System.out.println("thread2" + thread2.getState());
        System.out.println("thread1" + thread1.getState());

    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            syn();
        }

        private synchronized void syn() {
            try {
                Thread.sleep(1000);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            syn();
        }

        private synchronized void syn() {
            System.out.println(Thread.currentThread().getName() + "开始执行");
        }
    }

}
