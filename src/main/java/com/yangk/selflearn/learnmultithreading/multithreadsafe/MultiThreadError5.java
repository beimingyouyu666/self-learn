package com.yangk.selflearn.learnmultithreading.multithreadsafe;

/**
 * @Description 多线程安全问题3--初始化问题
 * 隐式溢出--注册监听事件
 * @Author yangkun
 * @Date 2020/2/21
 * @Version 1.0
 */
public class MultiThreadError5 {

    public static void main(String[] args) {
        Observer observer = new Observer();
        ConcreateSubject concreateSubject = new ConcreateSubject(observer);
        concreateSubject.doSomething();
    }

}

interface Subject {
    void doSomething();
}

class ConcreateSubject implements Subject {

    public ConcreateSubject(Observer observer) {
        this.observer = observer;
    }

    private Observer observer;

    @Override
    public void doSomething() {
        observer.receive();
    }
}

class Observer {
    public void receive() {
        System.out.println("receive subject doSomething");
    }
}
