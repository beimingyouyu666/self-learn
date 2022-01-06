package com.yangk.selflearn.learndesignpattern.decorator;

/**
 * @Description 场景类
 * @Author yangkun
 * @Date 2021/11/19
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new ConcreteDecorator1(component);
        component = new ConcreteDecorator2(component);
        component.operate();
    }
}
