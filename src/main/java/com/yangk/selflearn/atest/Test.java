package com.yangk.selflearn.atest;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2020/3/19
 * @Version 1.0
 * @blame yangkun
 */
public class Test {

    public static void main(String[] args) {
        Data data = new Data();
        data.setName("111");
        data.setPass("222");
        String s = JSONObject.toJSONString(data);
        System.out.println(s);
        Data1 data1 = JSONObject.parseObject(s, Data1.class);
        System.out.println(data1);

    }
}
