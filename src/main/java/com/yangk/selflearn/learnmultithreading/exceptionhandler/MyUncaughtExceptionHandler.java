package com.yangk.selflearn.learnmultithreading.exceptionhandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 自己实现全局的异常处理器
 * @Author yangkun
 * @Date 2020/2/20
 * @Version 1.0
 */
@Slf4j
@Data
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("线程：{}抛出异常", t.getName());
    }
}
