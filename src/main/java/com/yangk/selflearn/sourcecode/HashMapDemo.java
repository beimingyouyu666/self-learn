package com.yangk.selflearn.sourcecode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/12/13
 * @Version 1.0
 * @blame yangkun
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Collections.synchronizedMap(new HashMap<>());

        Map<String, String> map1 = new HashMap<>(32);
        Map<String, String> map = new HashMap<>();
        String put = map.put("1", "1");
        String s = map.get("1");
        String remove = map.remove("1");
    }

}
