package com.yangk.selflearn.designpattern.strategy;

/**
 * @Description 具体的策略1
 * @Author yangkun
 * @Date 2019/8/13
 * @Version 1.0
 */
public class ConcreateStrategy1 implements IStrategy {
    @Override
    public void operate() {
        System.out.println("ConcreateStrategy1 操作1111111111111");
    }
}
