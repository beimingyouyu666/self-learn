package com.yangk.mystudy.designpattern.strategy;

/**
 * @Description 环境类
 * @Author yangkun
 * @Date 2019/8/13
 * @Version 1.0
 */
public class Context {

    private IStrategy iStrategy;

    public Context(IStrategy iStrategy) {
        this.iStrategy = iStrategy;
    }

    public void operate() {
        iStrategy.operate();
    }

}
