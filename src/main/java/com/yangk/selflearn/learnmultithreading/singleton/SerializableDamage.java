package com.yangk.selflearn.learnmultithreading.singleton;

import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description 为了演示双重检测被反序列化破坏 -- 简单实现
 * @Author yangkun
 * @Date 2020/2/28
 * @Version 1.0
 * @blame yangkun
 */
public class SerializableDamage {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:temp.file"));
        objectOutputStream.writeObject(SingletonDoubleCheck.getInstance());

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:temp.file"));
        SingletonDoubleCheck singletonDoubleCheck = (SingletonDoubleCheck)objectInputStream.readObject();

        // 追踪 readObject方法查看底层

        // SingletonDoubleCheck 未加上readResolve方法时，打印 false -- 因为通过反射重新创建了对象
        // SingletonDoubleCheck 加上readResolve方法后，打印 true -- 通过定义的方法返回了定义的单例对象
        System.out.println(singletonDoubleCheck == SingletonDoubleCheck.getInstance());

        String s = JSON.toJSONString(Singleton8.INSTANCE);
        Singleton8 singleton8 = JSON.parseObject(s, Singleton8.class);
        System.out.println(singleton8 == Singleton8.INSTANCE);


    }
}
