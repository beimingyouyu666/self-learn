package com.yangk.selflearn.learnmultithreading.juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description threadlocal运用
 *  将SimpleDateFormat放在threadlocal里面，每个线程一个SimpleDateFormat对象，避免多次重复创建SimpleDateFormat
 *
 *  todo 如果获取线程池返回值
 * @Author yangkun
 * @Date 2020/9/25
 * @Version 1.0
 * @blame yangkun
 */
public class ThreadLocalPractice {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Set<Object> result = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Future<?> submit = executorService.submit(() -> {
//                System.out.println(format);
                result.add(format(finalI));
            });
//            result.add(submit.get());
        }
        System.out.println(result.size());
        System.out.println(result);
    }

    public static String format(int second) {
        Date date = new Date(second * 1000);
        return threadLocal.get().format(date);
    }
}
