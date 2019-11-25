package com.yangk.selflearn.designpattern.factory.simplefactory;

import com.yangk.selflearn.designpattern.factory.standardfactory.AbstractFactory;
import com.yangk.selflearn.designpattern.factory.standardfactory.ConcreteFactory;
import com.yangk.selflearn.designpattern.factory.standardfactory.ConcreteProduct1;

/**
 * @Description 简单工厂模式应用类
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class SimpleClient {
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory();
        ConcreteProduct1 concreteProduct1 = SimpleFactory.create(ConcreteProduct1.class);
        concreteProduct1.sayMiao();
    }


}
