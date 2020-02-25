package com.yangk.selflearn.multi;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/14
 * @Version 1.0
 * @blame yangkun
 */
@Slf4j
public class MultiThreadingTest {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024), new ThreadFactoryBuilder().setNameFormat("test multi").build());

    public static void main(String[] args) {
        MultiThreadingThreadLocalTest test = new MultiThreadingThreadLocalTest();
        test.add();
        for (int i = 0; i < 3; i++) {
            User user1 = MultiThreadingThreadLocalTest.userThreadLocal.get();
            log.info("before handle,user is :{}", MultiThreadingThreadLocalTest.userThreadLocal.get());

            if (user1 == null) {
                User user = new User(i + "", i + "");
                MultiThreadingThreadLocalTest.userThreadLocal.set(user);
            } else {
                user1.setId(user1.getId() + "-" + i);
                user1.setName(user1.getName() + "-" + i);
                MultiThreadingThreadLocalTest.userThreadLocal.set(user1);
            }
            log.info("after handle,user is :{}", MultiThreadingThreadLocalTest.userThreadLocal.get());
        }

        threadPoolExecutor.execute(() -> {
            User user1 = MultiThreadingThreadLocalTest.userThreadLocal.get();
            log.info("before handle,user is :{}", MultiThreadingThreadLocalTest.userThreadLocal.get());
            if (user1 == null) {
                User user = new User(1 + "", 1 + "");
                MultiThreadingThreadLocalTest.userThreadLocal.set(user);
            } else {
                user1.setId(user1.getId() + "-" + 1);
                user1.setName(user1.getName() + "-" + 1);
                MultiThreadingThreadLocalTest.userThreadLocal.set(user1);
            }
            log.info("after handle,user is :{}", MultiThreadingThreadLocalTest.userThreadLocal.get());
        });

        threadPoolExecutor.execute(() -> {
            User user1 = MultiThreadingThreadLocalTest.userThreadLocal.get();
            log.info("before handle,user is :{}", MultiThreadingThreadLocalTest.userThreadLocal.get());
            if (user1 == null) {
                User user = new User(2 + "", 2 + "");
                MultiThreadingThreadLocalTest.userThreadLocal.set(user);
            } else {
                user1.setId(user1.getId() + "-" + 2);
                user1.setName(user1.getName() + "-" + 2);
                MultiThreadingThreadLocalTest.userThreadLocal.set(user1);
            }
            log.info("after handle,user is :{}", MultiThreadingThreadLocalTest.userThreadLocal.get());
        });

    }

}
