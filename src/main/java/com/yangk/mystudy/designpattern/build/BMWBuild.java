package com.yangk.mystudy.designpattern.build;

/**
 * @Description 宝马建造者
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public class BMWBuild extends AbstractBuild {

    private BMWCar bmwCar = new BMWCar();

    @Override
    public Car build() {
        return this.bmwCar;
    }

    @Override
    public void setPart() {
        System.out.println(111);
        System.out.println(222);
        System.out.println(333);
    }
}
