package com.yangk.selflearn.learnmultithreading.exceptionhandler;

/**
 * @Description 使用自己实现的全局异常处理器
 * @Author yangkun
 * @Date 2020/2/20
 * @Version 1.0
 */
public class UseMyUncaughtExceptionHandler implements Runnable{

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("自己的"));
        new Thread(new UseMyUncaughtExceptionHandler(),"thread1").start();
        new Thread(new UseMyUncaughtExceptionHandler(),"thread1").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}

