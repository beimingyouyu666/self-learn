package com.yangk.selflearn.learnmultithreading.threadcreate;

/**
 * @Description 继承thread方式新建线程
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("继承thread方式新建线程");
    }


    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        threadStyle.start();
    }

}
