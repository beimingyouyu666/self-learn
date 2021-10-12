package com.yangk.selflearn.sourcecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2021/7/15
 * @Version 1.0
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        List<String> list1 = new ArrayList<String>(list);
    }
}
