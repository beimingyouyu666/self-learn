package com.yangk.selflearn.limao.firstquarter.springrelated;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 关于事务传播行为的记录代码--nested
 *  事务B回滚只会回滚方法B内操作；事务A回滚会将方法A和方法B的所有操作都回滚
 *
 * @Author yangkun
 * @Date 2020/1/13
 * @Version 1.0
 * @blame yangkun
 */
@Service
public class TransactionalNested {

    @Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
    public void methodA() {
        System.out.println("methodA start tranactional");

        System.out.println("methodA end tranactional");
    }

    @Transactional(rollbackFor=Exception.class,propagation = Propagation.NESTED)
    public void methodB() {
        System.out.println("methodA start tranactional");

        System.out.println("methodA end tranactional");
    }
}
