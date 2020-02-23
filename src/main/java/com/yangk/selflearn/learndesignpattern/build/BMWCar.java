package com.yangk.selflearn.learndesignpattern.build;

/**
 * @Description 宝马
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public class BMWCar implements Car {
    @Override
    public void start() {
        System.out.println("宝马启动");
    }

    @Override
    public void run() {
        System.out.println("宝马跑起来");
    }

    @Override
    public void stop() {
        System.out.println("宝马停止");
    }
}
