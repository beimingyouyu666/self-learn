package com.yangk.selflearn.designpattern.state;

/**
 * @Description 具体状态类1
 * @Author yangkun
 * @Date 2019/8/8
 * @Version 1.0
 */
public class State1 extends AbstractState {

    /**
     * 本状态需要完成的任务
     */
    @Override
    public void handle1() {
        System.out.println("处理自身状态类任务handle1");
    }

    /**
     * 本状态如何过渡到其他状态
     */
    @Override
    public void handle2() {
        // 状态转变为state2
        super.getContext().setState(Context.state2);
        // 过渡到state2状态，由contxt实现
        super.getContext().handle2();
    }
}
