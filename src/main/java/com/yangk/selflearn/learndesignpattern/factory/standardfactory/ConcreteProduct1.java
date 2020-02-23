package com.yangk.selflearn.learndesignpattern.factory.standardfactory;

/**
 * @Description 工厂模式具体产品1
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ConcreteProduct1 extends AbstractProduct {
    @Override
    public void sayWang() {
        System.out.println("wangwang1");
    }

    @Override
    public void sayMiao() {
        System.out.println("miaomiao1");
    }
}
