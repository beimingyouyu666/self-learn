package com.yangk.selflearn.base.dao;

import com.yangk.selflearn.base.dos.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryUserById() {
        User user = userMapper.queryUserById("1");
        System.out.println(111);
    }

    @Test
    public void queryUsersByIds() {
        List<User> users = userMapper.queryUsersByIds(new ArrayList<>());
    }
}