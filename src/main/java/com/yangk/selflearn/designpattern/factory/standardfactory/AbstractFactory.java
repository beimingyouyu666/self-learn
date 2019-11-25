package com.yangk.selflearn.designpattern.factory.standardfactory;

/**
 * @Description 标准工厂模式抽象工厂
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public abstract class AbstractFactory {

    protected abstract <T extends AbstractProduct> T create(Class<T> clazz);
}
