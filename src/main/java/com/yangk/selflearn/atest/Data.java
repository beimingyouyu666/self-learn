package com.yangk.selflearn.atest;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2020/3/19
 * @Version 1.0
 * @blame yangkun
 */
@lombok.Data
public class Data implements Serializable {
    private static final long serialVersionUID = -6372697611014170251L;

    private String name;
    private String pass;
}
