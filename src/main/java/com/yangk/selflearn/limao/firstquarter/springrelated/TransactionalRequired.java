package com.yangk.selflearn.limao.firstquarter.springrelated;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 关于事务传播行为的记录代码--默认的required
 * 方法B的传播行为是required，当方法A调用方法B时，方法B不会新开事务，就会在事务A内
 *                            当方法C调用方法B时，方法B不会有事务，因为方法C是没有事务的
 * @Author yangkun
 * @Date 2020/1/13
 * @Version 1.0
 * @blame yangkun
 */
@Service
public class TransactionalRequired {

    @Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
    public void methodA() {
        System.out.println("methodA start tranactional");
        System.out.println("methodA excute");
        methodB();
        System.out.println("methodA end tranactional");
    }

    @Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
    public void methodB() {
        System.out.println("methodA start tranactional");
        System.out.println("methodB excute");
        int i = 0/0;
        System.out.println("methodA end tranactional");
    }

    public void methodC() {
        System.out.println("methodC excute");
        methodB();
    }
}
