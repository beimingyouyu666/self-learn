package com.yangk.mystudy.designpattern.factory.manyfactory;

import com.yangk.mystudy.designpattern.factory.standardfactory.AbstractProduct;

/**
 * @Description 多工厂模式抽象工厂
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public abstract class AbstractFactory {

    protected abstract <T extends AbstractProduct> T create();
}
