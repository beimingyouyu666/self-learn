package com.yangk.selflearn.concurrency.synchronizeds;

/**
 * @Description 反编译出字节码查看monitor两个指令
 * 1、先编译java文件为class字节码文件：javac DecomplierDemo.java
 * 2、将字节码文件反编译出来查看：javap -verbose DecomplierDemo.class
 * monitorenter与monitorexit两个指令
 * <p>
 * public static void main(java.lang.String[]);
 * descriptor: ([Ljava/lang/String;)V
 * flags: ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=2, locals=4, args_size=1
 * 0: new           #2                  // class java/lang/Object
 * 3: dup
 * 4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 7: astore_1
 * 8: aload_1
 * 9: dup
 * 10: astore_2
 * 11: monitorenter
 * 12: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 15: sipush        666
 * 18: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 * 21: aload_2
 * 22: monitorexit
 * 23: goto          31
 * 26: astore_3
 * 27: aload_2
 * 28: monitorexit
 * 29: aload_3
 * 30: athrow
 * 31: return
 * Exception table:
 * from    to  target type
 * 12    23    26   any
 * 26    29    26   any
 * @Author yangkun
 * @Date 2020/1/10
 * @Version 1.0
 */
public class DecomplierDemo {

    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o) {
            System.out.println(666);
        }
    }
}
