package com.yangk.mystudy.designpattern.observer;

import java.util.Observable;

/**
 * @Description 具体的被观察者
 * @Author yangkun
 * @Date 2019/8/9
 * @Version 1.0
 */
public class HanFei extends Observable implements IHanFei {

    @Override
    public void eat() {
        System.out.println("韩非吃饭");
        super.setChanged();
        super.notifyObservers("吃饭");
    }

    @Override
    public void happy() {
        System.out.println("韩非happy");
        super.setChanged();
        super.notifyObservers("happy");
    }
}
