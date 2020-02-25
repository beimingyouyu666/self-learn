package com.yangk.selflearn.limao.jvmlearn;

/**
 * @Description 模拟栈内存溢出
 * jvm参数
 * -XX:ThreadStackSize=1m
 * @Author yangkun
 * @Date 2019/12/12
 * @Version 1.0
 * @blame yangkun
 */
public class StackOverFlowDemo {

    private static long count = 0;

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("调用了方法" + (++count) + "次");
        test();
    }

}
