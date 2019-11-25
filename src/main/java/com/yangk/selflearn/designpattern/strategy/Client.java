package com.yangk.selflearn.designpattern.strategy;

/**
 * @Description 实际使用类高层模块
 * @Author yangkun
 * @Date 2019/8/13
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        ConcreateStrategy1 concreateStrategy1 = new ConcreateStrategy1();
        Context context = new Context(concreateStrategy1);
        context.operate();
    }
}
