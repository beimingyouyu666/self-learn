package com.yangk.selflearn.learndesignpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description 具体的观察者lisi
 * @Author yangkun
 * @Date 2019/8/9
 * @Version 1.0
 */
public class LisiObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("lisi say: hanfei :" + arg);
    }
}
