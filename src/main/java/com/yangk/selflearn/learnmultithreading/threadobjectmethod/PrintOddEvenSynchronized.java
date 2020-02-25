package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 * 使用synchronized会导致cpu资源浪费，因为两个线程争抢锁，
 * 不一定是轮流着来的，抢到锁没打印就是浪费了一次
 * <p>
 * 利用synchronized代码块每次只能一个线程获取锁的特性，在代码中判断奇偶数，打印并加一
 * 使用位运算提高效率 (count & 1 ) == 0 就为偶数
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class PrintOddEvenSynchronized {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (count < 100) {
                synchronized (PrintOddEvenSynchronized.class) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + "：" + count++);
                    }
                }
            }
        }, "偶数").start();

        Thread.sleep(100);

        new Thread(() -> {
            while (count < 100) {
                synchronized (PrintOddEvenSynchronized.class) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + "：" + count++);
                    }
                }
            }
        }, "奇数").start();

    }

}
