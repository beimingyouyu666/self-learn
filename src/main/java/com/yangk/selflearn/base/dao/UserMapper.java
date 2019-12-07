package com.yangk.selflearn.base.dao;

import com.yangk.selflearn.base.dos.User;

public interface UserMapper {

    User queryUserById(String id);

}