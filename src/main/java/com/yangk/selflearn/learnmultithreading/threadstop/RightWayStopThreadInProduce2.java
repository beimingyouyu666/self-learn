package com.yangk.selflearn.learnmultithreading.threadstop;

/**
 * @Description 生产中正确停止线程的方式2：被线程调用的方法捕获异常后，重新设置中断状态
 * 调用方run方法就可以判断是否中断进行操作
 * @Author yangkun
 * @Date 2020/2/13
 * @Version 1.0
 */
public class RightWayStopThreadInProduce2 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduce2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("一直在执行");
            throwWay();
        }

    }

    /****
     *
     * @throws InterruptedException
     */
    private void throwWay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("重新设置中断状态");
            Thread.currentThread().interrupt();
        }
    }


}

