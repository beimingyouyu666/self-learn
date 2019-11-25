package com.yangk.selflearn.designpattern.factory.delayfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 延迟加载工厂模式
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class DelayFactory {

    // 如果要控制数量就将值设置为list类型
    private static Map<String, Object> map = new HashMap();

    public static <T> T getInstance(Class clazz) {
        Object instance = null;
        if (map.containsKey(clazz.getName())) {
            instance = map.get(clazz.getName());
        } else {
            try {
                instance = Class.forName(clazz.getName()).newInstance();
                map.put(clazz.getName(), instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) instance;
    }
}
