package com.yangk.mystudy.designpattern.state;

import lombok.Data;

/**
 * @Description 状态抽象类
 * @Author yangkun
 * @Date 2019/8/8
 * @Version 1.0
 */
@Data
public abstract class AbstractState {

    private Context context;

    public abstract void handle1();

    public abstract void handle2();

}
