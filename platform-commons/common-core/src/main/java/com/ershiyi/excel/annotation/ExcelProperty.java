package com.ershiyi.excel.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelProperty {
    String aliasName();
    String propertyName() default "";
    int showOrder() default 0;
    int defaultOrder();
}
