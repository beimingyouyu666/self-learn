package com.yangk.selflearn.learnmultithreading.threadstart;

/**
 * @Description 测试调用两次start方法
 * @Author yangkun
 * @Date 2020/2/12
 * @Version 1.0
 */
public class CannotStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }

}
