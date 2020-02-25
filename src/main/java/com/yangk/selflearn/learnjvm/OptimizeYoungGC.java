package com.yangk.selflearn.learnjvm;

/**
 * @Description 模拟年轻代gc情况，使用jstat，jmap，jhat命令查看内存情况
 * <p>
 * 每次young gc后基本没什么对象进入老年代，那么就不用优化
 * <p>
 * jvm参数：
 * -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:oldgc.log
 * @Author yangkun
 * @Date 2019/11/29
 * @Version 1.0
 * @blame yangkun
 */
public class OptimizeYoungGC {

    /**
     * @Description 一开始sleep30s为了先找到pid，然后“jstat -gc PID 1000 1000”查看gc情况
     * 代码模拟的是每秒产生5M对象
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [args]
     * @Return
     **/
    public static void main(String[] args) throws Exception {
        Thread.sleep(30000);
        while (true) {
            load();
        }
    }

    private static void load() throws Exception {
        byte[] data = null;
        long begin = System.currentTimeMillis();
        System.out.println("begin:" + begin);
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        System.out.println("end,cost:" + (System.currentTimeMillis() - begin));
        data = null;

        Thread.sleep(1000);
    }

}
