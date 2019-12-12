package com.yangk.selflearn.multi;

import lombok.Data;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/14
 * @Version 1.0
 * @blame yangkun
 */
@Data
public class User {

    private String name;

    private String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
