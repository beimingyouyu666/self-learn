package com.yangk.mystudy.designpattern.build;

/**
 * @Description 奔驰建造者
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public class BenzCar implements Car {
    @Override
    public void start() {
        System.out.println("奔驰启动");
    }

    @Override
    public void run() {
        System.out.println("奔驰跑起来");
    }

    @Override
    public void stop() {
        System.out.println("奔驰停止");
    }
}
