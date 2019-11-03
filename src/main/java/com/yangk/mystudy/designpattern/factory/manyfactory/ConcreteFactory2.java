package com.yangk.mystudy.designpattern.factory.manyfactory;

import com.yangk.mystudy.designpattern.factory.standardfactory.ConcreteProduct2;

/**
 * @Description 标准工厂模式具体工厂
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ConcreteFactory2 extends AbstractFactory {
    @Override
    public ConcreteProduct2 create() {
        return new ConcreteProduct2();
    }

}
