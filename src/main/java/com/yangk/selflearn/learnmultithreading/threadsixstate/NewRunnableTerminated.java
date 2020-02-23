package com.yangk.selflearn.learnmultithreading.threadsixstate;

/**
 * @Description 演示线程状态： New、 Runnable、 Terminated
 * @Author yangkun
 * @Date 2020/2/15
 * @Version 1.0
 */
public class NewRunnableTerminated implements Runnable{


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        // new
        System.out.println(thread.getState());
        thread.start();
        Thread.sleep(10);
        // runnable
        System.out.println(thread.getState());
        Thread.sleep(1000);
        // terminated
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (i % 10 == 0 ) {
                System.out.println(i+"是10的倍数");
            }
        }
    }
}
