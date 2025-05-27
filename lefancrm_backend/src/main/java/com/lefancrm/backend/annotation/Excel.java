package com.lefancrm.backend.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    String name() ;
    int excelIndex();
    String dateFormat() default "";
    String enumValue() default "";
}
