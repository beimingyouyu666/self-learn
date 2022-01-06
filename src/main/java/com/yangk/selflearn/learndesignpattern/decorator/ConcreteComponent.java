package com.yangk.selflearn.learndesignpattern.decorator;

/**
 * @Description 具体组件
 * @Author yangkun
 * @Date 2021/11/19
 * @Version 1.0
 */
public class ConcreteComponent extends Component {
    @Override
    public void operate() {
        System.out.println("ConcreteComponent执行");
    }
}
