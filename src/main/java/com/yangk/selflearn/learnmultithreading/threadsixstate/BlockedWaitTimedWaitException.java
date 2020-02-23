package com.yangk.selflearn.learnmultithreading.threadsixstate;

/**
 * @Description 演示线程状态： Blocked、 Waiting、 Timed_Waiting
 *  要用当前synchronize块的锁对象调用wait方法
 *  不然会报错：Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
 * @Author yangkun
 * @Date 2020/2/15
 * @Version 1.0
 */
public class BlockedWaitTimedWaitException {

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(10);
        thread2.start();
        Thread.sleep(10);
        // thread2此时没获取到锁，为blocked
        System.out.println("thread2"+thread2.getState());
        // thread1此时执行了sleep方法，为timed_waiting
        System.out.println("thread1"+thread1.getState());
        Thread.sleep(1000);
        // thread1此时执行到了wait方法，为waiting
        System.out.println("thread2"+thread2.getState());
        Thread.sleep(1000);
        System.out.println("thread1能否wait");
        System.out.println("thread1"+thread1.getState());

    }

    static class Thread1 extends Thread{

        @Override
        public void run() {
            synchronized (BlockedWaitTimedWaitException.class) {
                System.out.println(Thread.currentThread().getName()+"开始执行");
                try {
                    Thread.sleep(1000);
                    // 睡了一秒后释放锁，线程2开始执行

                    // 再睡一秒等线程2执行完，此处重新获取锁
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"睡醒");
                    // 这里要用当前synchronize块的锁对象调用wait方法，不然会报错
                    BlockedWaitTimedWaitException.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (BlockedWaitTimedWaitException.class) {
                System.out.println(Thread.currentThread().getName()+"开始执行");
            }
        }
    }

}
