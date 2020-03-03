package com.yangk.selflearn.learnmultithreading.singleton;

import java.io.Serializable;

/**
 * @Description 枚举（推荐使用） -- 代码简单；线程安全，反编译枚举文件可以看到枚举类是继承枚举父类，属性都是静态的；
 *                                 防止通过反射破坏单例,instance实例是final修饰的
 *
 *  javac编译如果报乱码错误，则使用：javac -encoding UTF-8 Singleton8.java
 *
 *  先 javac Singleton8.java 得到Singleton8.class;
 *  再javap -p Singleton8.class 得到以下类反编译后的详细信息
 *
 * public final class com.yangk.selflearn.learnmultithreading.singleton.Singleton8 extends java.lang.Enum<com.yangk
 * .selflearn.learnmultithreading.singleton.Singleton8> {
 *   public static final com.yangk.selflearn.learnmultithreading.singleton.Singleton8 INSTANCE;
 *
 *   private static final com.yangk.selflearn.learnmultithreading.singleton.Singleton8[] $VALUES;
 *
 *   public static com.yangk.selflearn.learnmultithreading.singleton.Singleton8[] values();
 *
 *   public static com.yangk.selflearn.learnmultithreading.singleton.Singleton8 valueOf(java.lang.String);
 *
 *   private com.yangk.selflearn.learnmultithreading.singleton.Singleton8();
 *
 *   public void whatEver();
 *
 *   static {};
 *
 * }
 *
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */

public enum  Singleton8 implements Serializable {

    INSTANCE;

    public void whatEver() {

    }
}
