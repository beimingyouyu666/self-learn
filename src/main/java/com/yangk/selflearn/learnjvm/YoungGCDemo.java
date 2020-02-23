package com.yangk.selflearn.learnjvm;

/**
 * @Description 模拟触发young gc
 *
 * jvm参数
 * “-XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:SurvivorRatio=8
 *  -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc-metaspaceover.log”
 *
    -- 原始日志
    Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for windows-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019 20:53:26 by "java_re" with MS VC++ 10.0 (VS2010)
    Memory: 4k page, physical 16687976k(4429252k free), swap 33374052k(19554568k free)
    CommandLine flags: -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MaxNewSize=5242880 -XX:NewSize=5242880 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
    0.289: [GC (Allocation Failure) 0.289: [ParNew: 3998K->511K(4608K), 0.0045648 secs] 3998K->2105K(9728K), 0.0047063 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
    Heap
    par new generation   total 4608K, used 3703K [0x00000000ff600000, 0x00000000ffb00000, 0x00000000ffb00000)
    eden space 4096K,  77% used [0x00000000ff600000, 0x00000000ff91dc98, 0x00000000ffa00000)
    from space 512K,  99% used [0x00000000ffa80000, 0x00000000ffafffc0, 0x00000000ffb00000)
    to   space 512K,   0% used [0x00000000ffa00000, 0x00000000ffa00000, 0x00000000ffa80000)
    concurrent mark-sweep generation total 5120K, used 1593K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
    Metaspace       used 3237K, capacity 4496K, committed 4864K, reserved 1056768K
    class space    used 350K, capacity 388K, committed 512K, reserved 1048576K


    -- 解析

    1、这里是打印jvm设置的参数
    CommandLine flags:
    -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MaxNewSize=5242880 -XX:NewSize=5242880 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760
    -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC
    -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC

    2、具体解析
    0.289: [GC (Allocation Failure) 0.289: [ParNew: 3998K->511K(4608K), 0.0045648 secs] 3998K->2105K(9728K), 0.0047063 secs]

    1、“0.289:”
    --指系统运行0.289s后发生此次GC

    2、“[ParNew: 3998K->511K(4608K), 0.0045648 secs]”
    ParNew: --指使用ParNew垃圾回收器进行年轻代的 young gc
    (4608K) --指的年轻代能使用的内存空间为4.5M -- 因为年轻代一共5M，e区是4M，两个s区各0.5M，正常情况下是e区和其中一个s区能分配
    3998K -- 指回收前年轻代已被使用3998K
    511K -- 指回收后年轻代还存活511K
    0.0045648 secs -- 指此次gc花费时间4.5ms

    3、3998K->2105K(9728K)
    9728K -- 这里指代的是整个堆内存的内存情况，整个堆区大小为9.5M，即老年代5M加上年轻代4.5M
    3998K -- 堆内存在回收前已被使用3998K
    2105K -- 堆内存在回收后被使用2105K


    4、[Times: user=0.01 sys=0.00, real=0.01 secs]
    指此次gc所花费的时间

    mvn clean package -Dmaven.test.skip=true -U

 * @Author yangkun
 * @Date 2019/11/25
 * @Version 1.0
 * @blame yangkun
 */
public class YoungGCDemo {

    public static void main(String[] args) {
        byte[] array1 = new byte[1024*1024];
        array1 = new byte[1024*1024];
        array1 = new byte[1024*1024];
        array1 = null;

        byte[] array2 = new byte[1024*1024];
    }

}
