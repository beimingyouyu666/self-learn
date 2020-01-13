package com.yangk.selflearn.limao.jvmlearn;

/**
 * @Description 测试大对象直接进入老年代
 *
 * -XX:NewSize=10485760  -XX:MaxNewSize=10485760  -XX:InitialHeapSize=20971520  -XX:MaxHeapSize=20971520  -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15  -XX:PretenureSizeThreshold=5242880  -XX:+UseParNewGC  -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails  -XX:+PrintGCTimeStamps  -Xloggc:objecttooldgc.log
 *
 * gc日志为：
 *
 * Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for windows-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019
 * 20:53:26 by "java_re" with MS VC++ 10.0 (VS2010)
 * Memory: 4k page, physical 16687976k(8569304k free), swap 33374052k(23093700k free)
 * CommandLine flags: -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=10485760
 * -XX:MaxTenuringThreshold=15 -XX:NewSize=10485760 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=5242880
 * -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers
 * -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
 * Heap
 *  par new generation   total 9216K, used 3168K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 *   eden space 8192K,  38% used [0x00000000fec00000, 0x00000000fef18288, 0x00000000ff400000)
 *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
 *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 *  concurrent mark-sweep generation total 10240K, used 6144K [0x00000000ff600000, 0x0000000100000000,
 *  0x0000000100000000)
 *  Metaspace       used 3237K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
 *
 *   解析：
 *
 *   “concurrent mark-sweep generation total 10240K, used 6144K”
 *   可以看出6m的大数组直接进入老年代
 *
 *
 *   大对象如果超过设置的参数会直接进入老年代，如果超过eden区大小也会直接进入老年代
 *
 *
 * @Author yangkun
 * @Date 2019/11/27
 * @Version 1.0
 * @blame yangkun
 */
public class BigObjectToOld {

    public static void main(String[] args) {
        byte[] array1 = new byte[6*1024*1024];
    }

}
