package com.yangk.selflearn.learndesignpattern.factory.standardfactory;

/**
 * @Description 标准工厂模式具体工厂
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ConcreteFactory extends AbstractFactory {
    @Override
    public <T extends AbstractProduct> T create(Class<T> clazz) {
        AbstractProduct product = null;
        try {
            product = (AbstractProduct) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
