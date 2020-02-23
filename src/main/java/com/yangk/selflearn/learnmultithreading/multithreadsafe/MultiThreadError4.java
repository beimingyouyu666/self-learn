package com.yangk.selflearn.learnmultithreading.multithreadsafe;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 多线程安全问题3--初始化问题
 *
 *  在构造函数中未初始化完毕就将this赋值，导致有些值还没初始化
 *
 * @Author yangkun
 * @Date 2020/2/21
 * @Version 1.0
 */
public class MultiThreadError4 {

    public static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(50);
        System.out.println(point.getX());
        System.out.println(point.getY());

    }
}
@Data
class Point{

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadError4.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    private int x;
    private int y;
}

class PointMaker extends Thread {

    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}