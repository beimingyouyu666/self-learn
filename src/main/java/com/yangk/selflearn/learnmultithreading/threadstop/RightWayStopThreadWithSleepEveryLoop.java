package com.yangk.selflearn.learnmultithreading.threadstop;

/**
 * @Description 每次迭代时线程都会阻塞情况下停止线程，
 * 那么在循环判断条件中不用去判断线程是否中断
 * <p>
 * 抛异常：java.lang.InterruptedException: sleep interrupted
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int count = 0;
            // 此处不用加判断 !Thread.currentThread().isInterrupted()，循环也会停止
            try {
                while (count <= 30000) {
                    if (count % 100 == 0) {
                        System.out.println(count + "是100的倍数");
                    }
                    count++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }
}
