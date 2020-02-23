package com.yangk.selflearn.learnmultithreading.multithreadsafe;

/**
 * @Description 多线程安全问题2--死锁
 *   thread1持有锁1，thread2持有锁2，都不释放，但是都想获取对方的锁，就出现死锁了。
 *
 * @Author yangkun
 * @Date 2020/2/21
 * @Version 1.0
 */
public class MultiThreadError2 implements Runnable {

    private static int flag = 0;

    private static Object o1= new Object();
    private static Object o2= new Object();

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError2 multiThreadError1 = new MultiThreadError2();
        Thread thread1 = new Thread(multiThreadError1);
        Thread thread2 = new Thread(multiThreadError1);
        thread1.start();
        Thread.sleep(50);
        flag=1;
        thread2.start();


    }

    @Override
    public void run() {
        if(flag ==0) {
            synchronized (o1) {
                System.out.println("flag=0,get o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("拿到o2");
                }
            }
        }
        if(flag ==1) {
            synchronized (o2) {
                System.out.println("flag=1,get o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("拿到o1");
                }
            }
        }
    }
}
