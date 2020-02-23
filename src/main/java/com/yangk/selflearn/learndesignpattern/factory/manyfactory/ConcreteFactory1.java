package com.yangk.selflearn.learndesignpattern.factory.manyfactory;

import com.yangk.selflearn.learndesignpattern.factory.standardfactory.ConcreteProduct1;

/**
 * @Description 标准工厂模式具体工厂
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ConcreteFactory1 extends AbstractFactory {
    @Override
    public ConcreteProduct1 create() {
        return new ConcreteProduct1();
    }

}
