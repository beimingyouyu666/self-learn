package com.yangk.mystudy.designpattern.factory.delayfactory;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class DelayClient {

    public static void main(String[] args) {
        User instance = DelayFactory.getInstance(User.class);
        System.out.println(instance);
    }
}
