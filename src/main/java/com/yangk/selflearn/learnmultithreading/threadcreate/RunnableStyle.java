package com.yangk.selflearn.learnmultithreading.threadcreate;

/**
 * @Description 使用runnable方式新建线程
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("使用runnable方式新建线程");
    }
}
