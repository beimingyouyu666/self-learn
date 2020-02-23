package com.yangk.selflearn.learnmultithreading.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 指令重排序
 *
 *  出现了：x=0y=0，说明代码的有可能的执行顺序为：y=a,a=1,x=b,b=1，thread2的两句代码进行了重排序
 *  第53443次:x=0y=1
 * 第53444次:x=0y=1
 * 第53445次:x=0y=1
 * 第53446次:x=0y=0
 * @Author yangkun
 * @Date 2020/2/23
 * @Version 1.0
 */
public class OutOfOrderExecution {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 1;
        while (true) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);
            Thread thread1 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread thread2 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            thread1.start();
            thread2.start();
            // 使用CountDownLatch，保证两个线程同时开始
            latch.countDown();
            thread1.join();
            thread2.join();
            String result = "第" + (count++) + "次:" + "x=" + x + "y=" + y;
            if (x==0 && y==0){
                System.out.println(result);
                break;
            }else {
                System.out.println(result);
            }
        }
    }

}
