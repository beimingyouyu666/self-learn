package com.yangk.selflearn.designpattern.state;

/**
 * @Description 调用类
 * @Author yangkun
 * @Date 2019/8/8
 * @Version 1.0
 */
public class Clint {

    public static void main(String[] args) {
        Context context = new Context();
        // 初始状态为state1
        context.setState(new State1());
        context.handle2();
        context.handle1();
    }

}
