package com.yangk.selflearn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description 测试关闭线程池
 * @Author yangkun
 * @Date 2019/8/15
 * @Version 1.0
 */
public class TestClosePool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            pool.execute(() -> {
                System.out.println("test+" + finalI);
            });
        }
        pool.shutdown();
//        pool.shutdownNow();
        System.out.println(System.currentTimeMillis());
        // 这里如果只等1ms，等待队列中还有任务，肯定是没终止的状态
        if (pool.awaitTermination(1, TimeUnit.MILLISECONDS)) {
            System.out.println(System.currentTimeMillis());
            System.out.println("pool closed!");
        }


    }

}
