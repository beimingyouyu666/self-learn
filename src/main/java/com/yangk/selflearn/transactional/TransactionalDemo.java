package com.yangk.selflearn.transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/9/17
 * @Version 1.0
 * @blame yangkun
 */
@Component
@Slf4j
public class TransactionalDemo {

    @Transactional(rollbackFor = Exception.class)
    public void one() {
        log.info("执行one方法");
        try {
            two();
        } catch (Exception e) {
            log.info("抛异常了");
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void two() {
        log.info("执行two方法");
    }

}
