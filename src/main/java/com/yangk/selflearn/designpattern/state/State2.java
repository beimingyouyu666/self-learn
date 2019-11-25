package com.yangk.selflearn.designpattern.state;

/**
 * @Description 具体状态类2
 * @Author yangkun
 * @Date 2019/8/8
 * @Version 1.0
 */
public class State2 extends AbstractState {

    /**
     * 本状态如何过渡到其他状态
     */
    @Override
    public void handle1() {

        // 状态转变为state1
        super.getContext().setState(Context.state1);
        // 过渡到state1状态，由contxt实现
        super.getContext().handle1();
    }

    /**
     * 本状态需要完成的任务
     */
    @Override
    public void handle2() {
        System.out.println("处理自身状态类任务handle2");
    }
}
