package com.yangk.selflearn.learndesignpattern.factory.replacesingltonfactory;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class Client {

    public static void main(String[] args) {
        Singleton sl = ReplaceSingltonFactory.create(Singleton.class);
        System.out.println(111);
    }

}
