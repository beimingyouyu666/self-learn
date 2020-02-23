package com.yangk.selflearn.learnmultithreading.threadstop;

/**
 * @Description 生产中正确停止线程的方式1：被线程调用的方法抛出异常，由run方法去处理异常
 * @Author yangkun
 * @Date 2020/2/13
 * @Version 1.0
 */
public class RightWayStopThreadInProduce1 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduce1());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("一直在执行");
                throwWay();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /****
     * 此方法抛出异常，让调用方处理异常，若此处捕获了异常则可能导致调用方出错，不会中断线程操作
     *      @Override
     *     public void run() {
     *             while (true) {
     *                 System.out.println("一直在执行");
     *                 throwWay();
     *             }
     *     }
     *
     *  private void throwWay() {
     *      try {
     *         Thread.sleep(2000);
     *      } catch (InterruptedException e) {
     *      e.printStackTrace();
     *      }
     *  }
     * @throws InterruptedException
     */
    private void throwWay() throws InterruptedException {
        Thread.sleep(2000);
    }


}
