package com.yangk.selflearn.limao.jvmlearn;

/**
 * @Description 模拟老年代gc
 * @Author yangkun
 * @Date 2019/11/27
 * @Version 1.0
 * @blame yangkun
 */
public class OldGCDemo {

    public static void main(String[] args) {

        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];

        byte[] array6 = new byte[2 * 1024 * 1024];


    }
}
