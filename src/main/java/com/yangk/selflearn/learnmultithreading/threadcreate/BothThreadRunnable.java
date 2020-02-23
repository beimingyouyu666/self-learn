package com.yangk.selflearn.learnmultithreading.threadcreate;

/**
 * @Description 同时用两种方式实现线程
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class BothThreadRunnable {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("通过thread");
            }
        }.start();
    }
}
