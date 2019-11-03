package com.yangk.mystudy.designpattern.factory.standardfactory;

/**
 * @Description 标准工厂模式应用类
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory();
        ConcreteProduct1 concreteProduct1 = factory.create(ConcreteProduct1.class);
        concreteProduct1.sayMiao();
        concreteProduct1.sayWang();
        ConcreteProduct2 concreteProduct2 = factory.create(ConcreteProduct2.class);
        concreteProduct2.sayMiao();
        concreteProduct2.sayWang();

    }


}
