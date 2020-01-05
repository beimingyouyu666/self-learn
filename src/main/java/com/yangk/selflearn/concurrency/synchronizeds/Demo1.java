package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 模拟不加synchronized关键字并发a++错误的场景
 * @Author yangkun
 * @Date 2020/1/6
 * @Version 1.0
 */
public class Demo1 implements Runnable {

    private static Demo1 demo1 = new Demo1();

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(demo1);
        Thread thread2 = new Thread(demo1);
        thread1.start();
        thread2.start();
        // 使用join方法，为了让前面的线程执行完再执行打印方法
        thread1.join();
        thread2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }
}
