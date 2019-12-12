package com.yangk.selflearn.base.dao;

import com.yangk.selflearn.base.dos.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User queryUserById(String id);

    List<User> queryUsersByIds(@Param("ids") List<String> ids);

}