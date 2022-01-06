package com.yangk.selflearn.learndesignpattern.decorator;

/**
 * @Description 具体装饰者2
 * @Author yangkun
 * @Date 2021/11/19
 * @Version 1.0
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        super.operate();
        doSomething();
    }

    private void doSomething() {
        System.out.println("ConcreteDecorator2执行");
    }
}
