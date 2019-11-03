package com.yangk.mystudy.designpattern.factory.standardfactory;

/**
 * @Description 工厂模式具体产品2
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ConcreteProduct2 extends AbstractProduct {
    @Override
    public void sayWang() {
        System.out.println("wangwang2");
    }

    @Override
    public void sayMiao() {
        System.out.println("miaomiao2");
    }
}
