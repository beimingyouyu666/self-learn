package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

/**
 * @Description join的普通用法
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("main线程的状态为：" + mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程执行完毕");
        thread.join();
        // thread.join() 可以用以下代码替代
//        synchronized (thread) {
//            thread.wait();
//        }
        // 这里就必须等子线程休眠10秒才会打印下面这句话
        // 如果不使用join方法，就会立即打印下面这句话
        System.out.println("子线程执行完毕");
    }
}
