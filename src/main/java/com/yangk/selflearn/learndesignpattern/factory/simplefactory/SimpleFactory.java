package com.yangk.selflearn.learndesignpattern.factory.simplefactory;

import com.yangk.selflearn.learndesignpattern.factory.standardfactory.AbstractProduct;

/**
 * @Description 简单工厂模式工厂类
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class SimpleFactory {

    public static <T extends AbstractProduct> T create(Class<T> clazz) {
        AbstractProduct product = null;
        try {
            product = (AbstractProduct) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
