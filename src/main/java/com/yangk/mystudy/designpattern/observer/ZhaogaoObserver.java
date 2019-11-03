package com.yangk.mystudy.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description 具体的观察者zhaogao
 * @Author yangkun
 * @Date 2019/8/9
 * @Version 1.0
 */
public class ZhaogaoObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("zhgao say: hanfei :" + arg);
    }
}
