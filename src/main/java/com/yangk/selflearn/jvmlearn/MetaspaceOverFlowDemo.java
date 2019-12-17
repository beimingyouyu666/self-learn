package com.yangk.selflearn.jvmlearn;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description 模拟metaspace内存溢出
 *
 * jvm参数
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 * 第二次测试 jvm 参数
 * -XX:MetaspaceSize=10M  -XX:MaxMetaspaceSize=10M
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails -Xloggc:gc-metaspaceover.log
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
 *
 * @Author yangkun
 * @Date 2019/12/12
 * @Version 1.0
 * @blame yangkun
 */
public class MetaspaceOverFlowDemo {

    public static void main(String[] args) {
        long count = 0;
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")){
                        System.out.println("启动前先检查");
                        return methodProxy.invokeSuper(o,objects);
                    }else {
                        return methodProxy.invokeSuper(o,objects);
                    }
                }
            });

            enhancer.create();
            System.out.println("目前创建了"+(++count)+"个汽车子类");
        }

    }

    static class Car {
        public void run(){
            System.out.println("汽车启动...");
        }
    }
}
