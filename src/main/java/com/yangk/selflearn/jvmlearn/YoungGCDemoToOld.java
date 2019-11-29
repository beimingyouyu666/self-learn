package com.yangk.selflearn.jvmlearn;

/**
 * @Description 年轻代gc测试对象进入老年代
 *
 * jvm参数
 * “-XX:NewSize=10485760  -XX:MaxNewSize=10485760  -XX:InitialHeapSize=20971520  -XX:MaxHeapSize=20971520  -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15  -XX:PretenureSizeThreshold=10485760  -XX:+UseParNewGC  -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps  -Xloggc:tooldgc第一段.log”
 *
 *  分析：年轻代10m，e区8m，s区各1m
 *  第一段：首先建了3个2m的数组，再建一个小数组，最后建2m数组内存肯定不够，引发young gc
 *  young gc 回收6m数组，剩余128kb内存保存到 s from区
 *
 *  第二段：又建了2个2m数组和一个128k数组，占用4m多
 *
 *
 * @Author yangkun
 * @Date 2019/11/26
 * @Version 1.0
 * @blame yangkun
 */
public class YoungGCDemoToOld {

    public static void main(String[] args) {

//        // 第一段
//        byte[] array1 = new byte[2*1024*1024];
//        array1 = new byte[2*1024*1024];
//        array1 = new byte[2*1024*1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128*1024];
//
//        byte[] array3 = new byte[2*1024*1024];
//
//        // 第二段
//        array3 = new byte[2*1024*1024];
//        array3 = new byte[2*1024*1024];
//        array3 = new byte[128*1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2*1024*1024];




    }

}
