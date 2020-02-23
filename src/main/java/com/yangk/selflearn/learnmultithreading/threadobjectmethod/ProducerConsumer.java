package com.yangk.selflearn.learnmultithreading.threadobjectmethod;

import java.util.Date;
import java.util.LinkedList;

/**
 * @Description 用wait/notify实现生产者消费者模式
 * @Author yangkun
 * @Date 2020/2/16
 * @Version 1.0
 */
public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        EventStronge eventStronge = new EventStronge();
        Producer producer = new Producer(eventStronge);
        Consumer consumer = new Consumer(eventStronge);
        new Thread(producer).start();
//        Thread.sleep(50);
        new Thread(consumer).start();
    }

}

class Producer implements Runnable {
    private EventStronge stronge;
    public Producer(EventStronge stronge) {
        this.stronge = stronge;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                stronge.put();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private EventStronge stronge;
    public Consumer(EventStronge stronge) {
        this.stronge = stronge;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                stronge.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EventStronge {
    private int size;
    private LinkedList<Date> list;
    public EventStronge() {
        this.size = 10;
        this.list = new LinkedList<>();
    }
    public synchronized void put() throws InterruptedException {
        while (list.size() == 10) {
            System.out.println("仓库满了");
            wait();
        }
        list.add(new Date());
        System.out.println("添加一个数据，仓库目前容量：" + list.size());
        notify();
    }
    public synchronized void take() throws InterruptedException {
        while (list.size() == 0) {
            System.out.println("仓库没数据了");
            wait();
        }
        System.out.println("拿走一个数据:" + list.poll() + "，仓库目前容量：" + list.size());
        notify();
    }
}
