package com.yangk.selflearn.idempotent.byserialnumber;

import java.lang.annotation.*;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IdempotentCache {
    String cacheKey();

    String[] uuidNames() default {};

    int lockExpiredTime() default 60;

    int cacheExpired() default 600;

    long blockTime() default 60L;

    boolean useMD5() default true;
}
