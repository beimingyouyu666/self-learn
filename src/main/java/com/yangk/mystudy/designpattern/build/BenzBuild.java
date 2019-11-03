package com.yangk.mystudy.designpattern.build;

/**
 * @Description 奔驰建造者
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public class BenzBuild extends AbstractBuild {

    private BenzCar benzCar = new BenzCar();

    @Override
    public Car build() {
        return this.benzCar;
    }

    @Override
    public void setPart() {
        System.out.println(333);
        System.out.println(111);
        System.out.println(222);
    }
}
