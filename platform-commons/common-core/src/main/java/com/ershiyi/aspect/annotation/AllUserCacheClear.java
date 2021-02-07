package com.ershiyi.aspect.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllUserCacheClear {
    String[] cacheNames() default {};
}
