package com.ershiyi.excel.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTitle {
    String title() default "";
    int merge() default 0;
    boolean isXlsx() default false;
}
