package com.yangk.mystudy.designpattern.state;

import lombok.Data;

/**
 * @Description 环境类
 * @Author yangkun
 * @Date 2019/8/8
 * @Version 1.0
 */
@Data
public class Context {

    // 当前状态
    private AbstractState state;

    // 定义状态
    public static State1 state1 = new State1();

    public static State2 state2 = new State2();

    // 设置当前状态
    public void setState(AbstractState state) {
        this.state = state;
        /*这个地方在debug的时候，会出现 Method threw 'java.lang.StackOverflowError' exception. Cannot evaluate com.yangk.mystudy
        .designpattern.state.SimpleClient.toString()
        因为state对象和contxt对象相互引用，Debugger会调用对象的toString()方法以显示对象中的所有数据信息。
        lombok的@data注解重写了tostring方法，程序会不断地调用state和context的toString()方法，永不停止，自然就造成了StackOverFlow的问题。*/

        // 设置当前状态的环境
        this.state.setContext(this);
    }

    //行为委托
    public void handle1() {
        state.handle1();

    }

    public void handle2() {
        state.handle2();
    }

}
