package com.yangk.selflearn.learndesignpattern.abstractfactory.kuozhan;

import com.yangk.selflearn.learndesignpattern.abstractfactory.AbstractKeyboard;
import com.yangk.selflearn.learndesignpattern.abstractfactory.AbstractMouse;
import com.yangk.selflearn.learndesignpattern.abstractfactory.ComputeFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/9/20
 * @Version 1.0
 * @blame yangkun
 */
@Slf4j
public class HuaweiFactory extends ComputeFactory {
    @Override
    public AbstractMouse createMouse() {
        log.info("new HuaweiMouse");
        return new HuaweiMouse();

    }

    @Override
    public AbstractKeyboard createKeyboard() {
        log.info("new HuaweiKeyboard()");
        return new HuaweiKeyboard();
    }
}
