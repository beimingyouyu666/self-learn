package com.yangk.selflearn.learnmultithreading.threadstop;


import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description 使用volatile标志位不能正确停止线程的实例
 *  这里使用阻塞队列的消费者生产者模式。一旦消费者消费不过来，生产者是会阻塞的
 *  消费者慢，生产者比较快，
 *  所以测试在生产者阻塞时，改变volatile的标记，看能否停止生产者线程--结果是不能停止
 *
 *  进行修复--使用interrupt方法
 * @Author yangkun
 * @Date 2020/2/14
 * @Version 1.0
 */
public class VolatileCanotStopThreadFixed {

    public static void main(String[] args) throws InterruptedException {
        VolatileCanotStopThreadFixed volatileCanotStopThread = new VolatileCanotStopThreadFixed();

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        Producer producer = volatileCanotStopThread.new Producer(arrayBlockingQueue);
        Thread threadProduce = new Thread(producer);
        threadProduce.start();
        Thread.sleep(1000);

        Consumer consumer = volatileCanotStopThread.new Consumer(arrayBlockingQueue);
        while (consumer.needConsume()){
            System.out.println("消费者消费数据");
            arrayBlockingQueue.take();
            Thread.sleep(10);
        }
        threadProduce.interrupt();
        System.out.println("消费者停止消费");
    }

    class Producer implements Runnable {

//        private volatile boolean cancel = false;

        ArrayBlockingQueue arrayBlockingQueue;

        public Producer(ArrayBlockingQueue arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (i<10000 && !Thread.currentThread().isInterrupted()) {
                    if (i % 100 ==0) {
                        System.out.println(i+"是100的倍数，加入队列中");
                        // 因为队列满足十个，就会阻塞在这个地方
                        arrayBlockingQueue.put(i);
                    }
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("结束生产");
            }
        }
    }

    class Consumer {

        ArrayBlockingQueue arrayBlockingQueue;

        public Consumer(ArrayBlockingQueue arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }


        public boolean needConsume() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }

}


