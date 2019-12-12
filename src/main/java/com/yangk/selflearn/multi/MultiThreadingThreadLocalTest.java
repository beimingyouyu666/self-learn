package com.yangk.selflearn.multi;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/14
 * @Version 1.0
 * @blame yangkun
 */
public class MultiThreadingThreadLocalTest {

    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public void add(){
        User user = new User("MultiThreadingThreadLocalTest","MultiThreadingThreadLocalTest");
        userThreadLocal.set(user);
    }

}
