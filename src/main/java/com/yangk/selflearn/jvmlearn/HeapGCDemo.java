package com.yangk.selflearn.jvmlearn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 模拟堆内存溢出
 *
 * jvm参数:-Xms10m -Xmx10m
 *
 * 当创建到360145个对象的时候，堆内存溢出
 * @Author yangkun
 * @Date 2019/12/12
 * @Version 1.0
 * @blame yangkun
 */
public class HeapGCDemo {

    private static long count = 0;

    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.out.println("目前创建了"+(++count)+"个对象");
        }
    }

}
