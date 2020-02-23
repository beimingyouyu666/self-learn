package com.yangk.selflearn.learndesignpattern.abstractfactory;

/**
 * @Description 电脑工厂类
 * @Author yangkun
 * @Date 2019/9/20
 * @Version 1.0
 * @blame yangkun
 */
public abstract class ComputeFactory {

    public abstract AbstractMouse createMouse();

    public abstract AbstractKeyboard createKeyboard();

}
