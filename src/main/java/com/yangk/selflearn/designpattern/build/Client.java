package com.yangk.selflearn.designpattern.build;

/**
 * @Description 场景类
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public class Client {

    public static void main(String[] args) {
        Director director = new Director();
        BenzCar benzCar = director.buildBenz();
        BMWCar bmwCar = director.buildBMW();
    }
}
