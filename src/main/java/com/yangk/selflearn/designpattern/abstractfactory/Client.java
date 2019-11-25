package com.yangk.selflearn.designpattern.abstractfactory;

import com.yangk.selflearn.designpattern.abstractfactory.kuozhan.HuaweiFactory;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/9/20
 * @Version 1.0
 * @blame yangkun
 */
public class Client {

    public static void main(String[] args) {
        // 生产戴尔产品
        ComputeFactory dellFactory = new DellFactory();
        AbstractKeyboard keyboard = dellFactory.createKeyboard();
        AbstractMouse mouse = dellFactory.createMouse();

        // 生产联想产品
        ComputeFactory lenovoFactory = new LenovoFactory();
        AbstractKeyboard keyboard1 = lenovoFactory.createKeyboard();
        AbstractMouse mouse1 = lenovoFactory.createMouse();

        // 扩展一个华为产品
        ComputeFactory huaweiFactory = new HuaweiFactory();
        AbstractKeyboard keyboard2 = huaweiFactory.createKeyboard();
        AbstractMouse mouse2 = huaweiFactory.createMouse();

    }


}
