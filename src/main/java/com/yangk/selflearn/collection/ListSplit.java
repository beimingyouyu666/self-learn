package com.yangk.selflearn.collection;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 集合拆分
 * @Author yangkun
 * @Date 2020/3/8
 * @Version 1.0
 */
@Slf4j
public class ListSplit {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        int size = 198;
        for (int i = 0; i < size; i++) {
            list.add("hello" + i);
        }
        List<List<String>> partition = Lists.partition(list, 10);
        int i = 0;
//        for (List<String> strings : partition) {
//            System.out.println(String.format("row:%s,size:%s,data:%s", ++i, strings.size(), strings));
//        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 1,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000),
                new ThreadFactoryBuilder().setNameFormat("threadyk-- %s").build());

        partition.forEach((list1) -> {
            threadPoolExecutor.submit(() -> {
                for (String s : list1) {
                    System.out.println(Thread.currentThread().getName()+":"+s);
                }
            });
        });

        threadPoolExecutor.shutdown();
        try {
            // 等待60分钟后判断线程池是否已终止，否则强制停止
            if (!threadPoolExecutor.awaitTermination(60,TimeUnit.SECONDS)) {
                threadPoolExecutor.shutdownNow();
                log.warn("pool shutdown fail,and force stop");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
