package com.yangk.selflearn.jvmlearn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 通过示例代码来使用 MAT软件分析内存
 * @Author yangkun
 * @Date 2019/12/10
 * @Version 1.0
 * @blame yangkun
 */
public class MatDemo {

    public static void main(String[] args) throws InterruptedException {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            dataList.add(new Data());
        }
        Thread.sleep(1 * 60 * 60 * 1000);
    }


    static class Data {

    }
}
