package com.yangk.selflearn.interview.limao;

public class _NIO {

    /**
     * 1.Buffer缓冲区几个常用参数
     * 这里有4个概念：capacity、limit、position、mark
     *
     * 这个capacity大概可以认为是缓冲区的容量大小，就是里面包含的数据的大小，
     * 比如说下面的代码举个例子，用字节数组（这个是最常见的数据结构）封装了一个ByteBuffer，可以看看里面的capacity是多少
     *
     *limit  读写数据的限制
     * position 开始读写的index，不能大于limit
     * mark  为了position 的复原
     *
     *2.使用Direct模式创建的缓冲区有什么作用？
     *少了一层jvm 的缓冲区
     *
     * 3.channel 和 Buffer  关系
     *  channl 负责和磁盘进行读写数据
     *  内存数据  ---->  buffer -------> channel----->  磁盘文件 或者 socket网络
     *
     * // 构造一个传统的文件输出流
     * 		FileOutputStream out = new FileOutputStream(
     * 				"F:\\development\\tmp\\hello.txt");
     * 		// 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
     * 		FileChannel channel = out.getChannel();
     *
     * 		ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());
     * 		channel.write(buffer);
     *
     * 		channel.close();
     * 		out.close();
     * 		.
     *
     *4.随机写和顺序写
     * 可以通过设置position 的位置  实现磁盘文件的随机写
     *
     *
     *
     *
     *
     *
     *
     *
     */

}
