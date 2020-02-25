package com.yangk.selflearn.learnmultithreading.threadstop;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 每次迭代时线程都会阻塞情况下停止线程，
 * 那么在循环判断条件中不用去判断线程是否中断
 * <p>
 * 如果try/catch放置在while循环内部，不会停止循环，因为sleep操作后，会清除中断标志
 * <p>
 * 抛异常：java.lang.InterruptedException: sleep interrupted
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
@Slf4j
public class RightWayStopThreadWithSleepEveryLoopTryInWhile {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int count = 0;
            // 此处加判断 !Thread.currentThread().isInterrupted()没用，不会停止循环
            while (!Thread.currentThread().isInterrupted() && count <= 30000) {
                if (count % 100 == 0) {
                    System.out.println(count + "是100的倍数");
                }
                count++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.error("抛出了异常：" + Thread.currentThread().getName());
                    e.printStackTrace();
                }
            }


        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }
}
