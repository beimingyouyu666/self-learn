package com.yangk.selflearn.limao.firstquarter.springrelated;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 关于事务传播行为的记录代码--requires_new
 * 方法A调用方法B时，方法B会新开事务，事务B回滚只是回滚方法B的操作，事务A回滚只是回滚方法A的操作
 * @Author yangkun
 * @Date 2020/1/13
 * @Version 1.0
 * @blame yangkun
 */
@Service
public class TransactionalRequiresNew {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void methodA() {
        System.out.println("methodA start tranactional");
        methodB();
        System.out.println("methodA end tranactional");
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void methodB() {
        System.out.println("methodA start tranactional");

        System.out.println("methodA end tranactional");
    }
}
