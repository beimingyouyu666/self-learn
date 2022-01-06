package com.yangk.selflearn.learndesignpattern.decorator;

/**
 * @Description 装饰者
 * @Author yangkun
 * @Date 2021/11/19
 * @Version 1.0
 */
public abstract class Decorator extends Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        this.component.operate();
    }
}
