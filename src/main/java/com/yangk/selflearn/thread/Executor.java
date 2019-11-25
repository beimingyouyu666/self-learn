package com.yangk.selflearn.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description 线程池的submit方法与execute方法区别
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class Executor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(() -> {
            test();
//            try {
//                test();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//            throw new NullPointerException("throw exception");
        });
        Object o = null;
        try {
            o = future.get();
            if (o == null) {
                System.out.println("ok");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getCause().getMessage());
        } catch (ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            System.out.println("===============");
            System.out.println(e.getMessage());
        }
        System.out.println(o);
    }

    public static void test() {
        int i = 0 / 0;
    }


}
