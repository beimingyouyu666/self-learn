package com.yangk.selflearn.learnmultithreading.threadstop;

/**
 * @Description 普通情况下停止线程
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int count = 0;
        // 加上一个判断标志，不然无法停止线程
        while (!Thread.currentThread().isInterrupted() && count <= Integer.MAX_VALUE / 2) {
            if (count % 10000 == 0) {
                System.out.println(count + "是10000的倍数");
            }
            count++;
        }
        System.out.println("任务结束");
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
