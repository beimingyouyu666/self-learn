package com.yangk.selflearn.learnmultithreading.exceptionhandler;

/**
 * @Description run方法内部抛出异常，线程是什么状态
 * @Author yangkun
 * @Date 2020/2/22
 * @Version 1.0
 */
public class RunMethodThrow implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RunMethodThrow());
        thread.start();
        Thread.sleep(50);
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
