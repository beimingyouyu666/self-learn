package com.yangk.selflearn.learndesignpattern.proxy;

/**
 * @Description 前置通知
 * @Author yangkun
 * @Date 2019/10/18
 * @Version 1.0
 * @blame yangkun
 */
public class PreAdvice implements Advice {
    @Override
    public void advice() {
        System.out.println("前置通知");
    }
}
