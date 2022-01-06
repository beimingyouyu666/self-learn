package com.yangk.selflearn.learndesignpattern.decorator;

/**
 * @Description 具体装饰者
 * @Author yangkun
 * @Date 2021/11/19
 * @Version 1.0
 */
public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        super.operate();
        doSomething();
    }

    private void doSomething() {
        System.out.println("ConcreteDecorator1执行");
    }
}
