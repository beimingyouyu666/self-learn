package com.yangk.selflearn.learnmultithreading.multithreadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 多线程安全问题3--初始化问题
 * <p>
 * 方法返回一个private对象，供给其他类使用，并不想让其他类修改；
 * 但是可能被修改，当其他类再访问就不是原始数据了
 * @Author yangkun
 * @Date 2020/2/21
 * @Version 1.0
 */
public class MultiThreadError3 {

    private Map<String, String> map;

    public MultiThreadError3() {
        this.map = new HashMap<>();
        map.put("1", "周一");
        map.put("2", "周二");
        map.put("3", "周三");
    }

    public Map<String, String> getMap() {
        return map;
    }

    public static void main(String[] args) throws InterruptedException {

        MultiThreadError3 multiThreadError3 = new MultiThreadError3();
        Map<String, String> map = multiThreadError3.getMap();
        System.out.println(map.get("1"));
        map.remove("1");
        System.out.println(map.get("1"));


    }


}
