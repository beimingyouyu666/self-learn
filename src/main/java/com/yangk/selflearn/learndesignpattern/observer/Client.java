package com.yangk.selflearn.learndesignpattern.observer;

/**
 * @Description 具体使用
 * @Author yangkun
 * @Date 2019/8/9
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        HanFei hanFei = new HanFei();
        hanFei.addObserver(new LisiObserver());
        hanFei.addObserver(new ZhaogaoObserver());
        hanFei.eat();
        hanFei.happy();
    }
}
