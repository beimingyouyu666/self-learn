package com.yangk.selflearn.limao.jvmlearn;

/**
 * @Description 优化老年代gc
 * <p>
 * jvm参数:
 * -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:oldgc.log
 * <p>
 * 优化后
 * -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800
 * -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:oldgc.log
 * @Author yangkun
 * @Date 2019/12/2
 * @Version 1.0
 * @blame yangkun
 */
public class OptimizeOldGC {

    /**
     * @Description 一开始sleep30s为了先找到pid，然后“jstat -gc PID 1000 1000”查看gc情况
     * 每秒产生80M对象，每秒都会触发young gc，但是gc后存活二十多M，s区放不下，就得进入老年代，使得频繁发生old gc
     * <p>
     * 优化：使每次young gc后的存活对象能放进s区，调整jvm参数，增大年轻代，并将“-XX:SurvivorRatio=2”即e：s1：s2 = 2:1:1
     * s区为50M可以放下每次young  gc 存活的对象
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [args]
     * @Return
     **/
    public static void main(String[] args) throws Exception {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }

    private static void loadData() throws Exception {
        byte[] array1 = null;
        for (int i = 0; i < 4; i++) {
            array1 = new byte[10 * 1024 * 1024];
        }
        byte[] array2 = new byte[10 * 1024 * 1024];
        byte[] array3 = new byte[10 * 1024 * 1024];
        byte[] array4 = new byte[10 * 1024 * 1024];
        array4 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }

}
