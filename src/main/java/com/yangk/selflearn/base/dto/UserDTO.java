package com.yangk.selflearn.base.dto;

import lombok.Data;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/29
 * @Version 1.0
 * @blame yangkun
 */
@Data
public class UserDTO implements Comparable<UserDTO> {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 用户名
     */
    private String username;

    public UserDTO(Integer id, Integer age, String password) {
        this.id = id;
        this.age = age;
        this.password = password;
    }

    public UserDTO() {
    }

    @Override
    public int compareTo(UserDTO userDTO) {
        return this.age.compareTo(userDTO.getAge());
    }
}
