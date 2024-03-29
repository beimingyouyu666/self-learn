package com.yangk.selflearn.designpattern.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 戴尔工厂
 * @Author yangkun
 * @Date 2019/9/20
 * @Version 1.0
 * @blame yangkun
 */
@Slf4j
public class DellFactory extends ComputeFactory {
    @Override
    public AbstractMouse createMouse() {
        log.info("new DellMouse");
        return new DellMouse();
    }

    @Override
    public AbstractKeyboard createKeyboard() {
        log.info("new DellKeyboard");
        return new DellKeyboard();
    }
}
