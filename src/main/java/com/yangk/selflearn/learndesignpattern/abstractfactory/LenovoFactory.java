package com.yangk.selflearn.learndesignpattern.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 联想工厂
 * @Author yangkun
 * @Date 2019/9/20
 * @Version 1.0
 * @blame yangkun
 */
@Slf4j
public class LenovoFactory extends ComputeFactory {
    @Override
    public AbstractMouse createMouse() {
        log.info("new LenovoMouse");
        return new LenovoMouse();
    }

    @Override
    public AbstractKeyboard createKeyboard() {
        log.info("new LenovoKeyboard");
        return new LenovoKeyboard();
    }
}
