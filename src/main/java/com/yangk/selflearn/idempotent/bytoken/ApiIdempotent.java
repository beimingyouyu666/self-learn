package com.yangk.selflearn.idempotent.bytoken;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 在需要保证 接口幂等性 的Controller的方法上使用此注解
 * @Author yangkun
 * @Date 2020/5/12
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
