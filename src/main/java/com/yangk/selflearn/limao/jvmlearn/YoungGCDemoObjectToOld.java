package com.yangk.selflearn.limao.jvmlearn;

/**
 * @Description 测试年轻代垃圾回收对象进入老年代
 * <p>
 * 打印的gc 日志:
 * <p>
 * Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for windows-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019
 * 20:53:26 by "java_re" with MS VC++ 10.0 (VS2010)
 * Memory: 4k page, physical 16687976k(4768320k free), swap 33374052k(17148340k free)
 * CommandLine flags: -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=10485760
 * -XX:MaxTenuringThreshold=15 -XX:NewSize=10485760 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760
 * -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers
 * -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
 * <p>
 * 0.262: [GC (Allocation Failure) 0.294: [ParNew: 7100K->1024K(9216K), 0.0141742 secs] 7100K->3143K(19456K), 0
 * .0464435 secs] [Times: user=0.00 sys=0.00, real=0.05 secs]
 * <p>
 * Heap
 * par new generation   total 9216K, used 5488K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 * eden space 8192K,  54% used [0x00000000fec00000, 0x00000000ff05c160, 0x00000000ff400000)
 * from space 1024K, 100% used [0x00000000ff500000, 0x00000000ff600000, 0x00000000ff600000)
 * to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
 * concurrent mark-sweep generation total 10240K, used 2119K [0x00000000ff600000, 0x0000000100000000,
 * 0x0000000100000000)
 * Metaspace       used 3238K, capacity 4496K, committed 4864K, reserved 1056768K
 * class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
 * <p>
 * 解析：
 * <p>
 * “ParNew: 7100K->1024K(9216K)”
 * 年轻代共9216K（e区和s from 区），gc前7100K，即6m+128kb，还有其余828K占用
 * gc后存活对象有2m和828K额外占用，这些s from区放不下，会进入老年代
 * <p>
 * -- “concurrent mark-sweep generation total 10240K, used 2119K ” 这里很直接看出来2m的那个数组进入了老年代
 * -- “from space 1024K, 100% used ” s from区100%被占用，应该是那其余828K内存占用
 * <p>
 * “7100K->3143K(19456K)”
 * 老年代一共19465K，gc前占用7100K，即年轻代被占用7100K，gc后占用3143K，即老年代那里2119K，加上那其余828K内存占用，差值196K应该是其他一些占用
 * <p>
 * “eden space 8192K,  54% used ”
 * 这里很奇怪，应该此时年轻代就是那个新建的2m数组，不应该占用这么多内存
 * @Author yangkun
 * @Date 2019/11/27
 * @Version 1.0
 * @blame yangkun
 */
public class YoungGCDemoObjectToOld {

    public static void main(String[] args) {
        /**
         分析：先是6m，再128kb，再2m肯定放不下，young gc后有2m垃圾加上内存中的其他数据，这些在s区放不下，所以进入老年代
         */
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];

        byte[] array2 = new byte[128 * 1024];
        array2 = null;

        byte[] array3 = new byte[2 * 1024 * 1024];
    }
}
