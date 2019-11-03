package com.yangk.mystudy.reflect;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotationParamter {
}
