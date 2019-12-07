package com.yangk.selflearn.maintest;

import com.yangk.selflearn.base.dos.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description list里面装user的list，想把内层所有user放到一个集合中
 *
 * @Author yangkun
 * @Date 2019/11/28
 * @Version 1.0
 * @blame yangkun
 */
public class SteamTest {

    public static void main(String[] args) {
        // TODO: 2019/11/28  流操作没实现

        List<List<User>> list = new ArrayList<>();
        List<User> list1 = Arrays.asList(new User(1,1,"1"),new User(2,2,"2"));
        List<User> list2 = Arrays.asList(new User(3,3,"3"),new User(4,4,"4"));
        List<User> list3 = Arrays.asList(new User(5,5,"5"),new User(6,6,"6"));

        list.add(list1);
        list.add(list2);
        list.add(list3);

        List<User> users = new ArrayList<>();
        List<List<User>> collect = list.stream().map((l) -> {
            users.addAll(l);
            return users;
        }).collect(Collectors.toList());
        System.out.println(users);
    }
}
