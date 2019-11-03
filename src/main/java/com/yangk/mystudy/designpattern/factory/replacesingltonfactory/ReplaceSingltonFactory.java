package com.yangk.mystudy.designpattern.factory.replacesingltonfactory;

import java.lang.reflect.Constructor;

/**
 * @Description 代替单例工厂模式工厂
 * @Author yangkun
 * @Date 2019/9/19
 * @Version 1.0
 * @blame yangkun
 */
public class ReplaceSingltonFactory {

    public static <T> T create(Class clazz) {
        T instance = null;
        try {
            Class aClass = Class.forName(clazz.getName());
            Constructor declaredConstructor = aClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            instance = (T) declaredConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;

    }
}
