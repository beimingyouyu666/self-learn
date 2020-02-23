package com.yangk.selflearn.learnmultithreading.threadstop;

/**
 * @Description 线程阻塞情况下停止线程
 * 报错：java.lang.InterruptedException: sleep interrupted
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class RightWayStopThreadWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int count = 0;
            // 这里加不加都行，此例只是为了验证在sleep时进行interrupt情况
            try {
                while (!Thread.currentThread().isInterrupted() && count <= 300) {
                    if (count % 100 == 0) {
                        System.out.println(count + "是100的倍数");
                    }
                    count++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
        // 使得在thread sleep的时候去停止
        Thread.sleep(500);
        thread.interrupt();

    }

}
