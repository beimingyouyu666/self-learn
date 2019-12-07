package com.yangk.selflearn.base.dos;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`user`")
@Data
public class User{
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 年龄
     */
    @Column(name = "`age`")
    private Integer age;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 性别
     */
    @Column(name = "`sex`")
    private Integer sex;

    /**
     * 用户名
     */
    @Column(name = "`username`")
    private String username;

    public User(Integer id,Integer age, String password) {
        this.id = id;
        this.age = age;
        this.password = password;
    }

    public User() {
    }

}