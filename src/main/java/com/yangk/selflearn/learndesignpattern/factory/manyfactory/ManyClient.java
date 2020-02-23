package com.yangk.selflearn.learndesignpattern.factory.manyfactory;

import com.yangk.selflearn.learndesignpattern.factory.standardfactory.ConcreteProduct1;
import com.yangk.selflearn.learndesignpattern.factory.standardfactory.ConcreteProduct2;

/**
 * @Description 多工厂使用类
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ManyClient {

    public static void main(String[] args) {
        ConcreteProduct1 concreteProduct1 = new ConcreteFactory1().create();
        concreteProduct1.sayMiao();

        ConcreteProduct2 concreteProduct2 = new ConcreteFactory2().create();
        concreteProduct2.sayMiao();
    }
}
