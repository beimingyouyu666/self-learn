package com.yangk.selflearn.designpattern.build;

/**
 * @Description 抽象建造者
 * @Author yangkun
 * @Date 2019/10/14
 * @Version 1.0
 * @blame yangkun
 */
public abstract class AbstractBuild {

    /**
     * @Description 创建car
     * @Author yangkun
     * @Date 2019/10/14
     * @Param []
     * @Return
     **/
    public abstract Car build();

    /**
     * @Description 设置部分属性--设置顺序可能不一样
     * @Author yangkun
     * @Date 2019/10/14
     * @Param []
     * @Return
     **/
    public abstract void setPart();

}
