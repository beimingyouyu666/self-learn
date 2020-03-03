package com.yangk.selflearn.collection;

import com.yangk.selflearn.base.dos.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2020/2/26
 * @Version 1.0
 * @blame yangkun
 */
public class ListFilter {

    public static void main(String[] args) {
        List<User> users1 = Arrays.asList(new User(1, 1, "11"), new User(2, 2, "22"));
        List<User> users2 = Arrays.asList(new User(1, 33, "11"), new User(2, 2, "22"), new User(3, 33, "33"));
//        List<User> union = users.stream().filter(u->{
//            return emps.stream().filter(e->u.getId()==e.getId()).count()<=0;
//        }).collect(Collectors.toList());
        List<User> collect = users1.stream().filter(u1 -> {
            return users2.stream().filter(u2 -> u2.getId().equals(u1.getId()) && u2.getAge().equals(u1.getAge())).count() == 1;
        }).collect(Collectors.toList());
        System.out.println(collect);


        Set<String> set1 = new HashSet<>();

    }
}
