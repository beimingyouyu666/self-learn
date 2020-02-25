package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description 用两个线程交替打印奇偶数--自己最初的实现
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class PrintOddEvenSelf {

    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (PrintOddEvenSelf.class) {
                while (count <= 100) {
                    if (count % 2 == 0) {
                        System.out.println("thread1打印：" + count);
                        count++;
                        try {
                            PrintOddEvenSelf.class.notify();
                            if (count <= 100) {
                                PrintOddEvenSelf.class.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        });

        Thread thread2 = new Thread(() -> {
            synchronized (PrintOddEvenSelf.class) {
                while (count <= 100) {
                    if (count % 2 == 1) {
                        System.out.println("thread2打印：" + count);
                        count++;
                        try {
                            PrintOddEvenSelf.class.notify();
                            if (count <= 100) {
                                PrintOddEvenSelf.class.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        });

        thread1.start();
        Thread.sleep(50);
        thread2.start();
    }

}
