package com.yangk.selflearn.designpattern.build;

/**
 * @Description 导演类--避免高层模块深入建造者内部
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public class Director {

    private BenzBuild benzBuild = new BenzBuild();

    private BMWBuild bmwBuild = new BMWBuild();

    public BMWCar buildBMW() {
        this.bmwBuild.setPart();
        return (BMWCar) this.bmwBuild.build();
    }

    public BenzCar buildBenz() {
        this.benzBuild.setPart();
        return (BenzCar) this.benzBuild.build();
    }


}
