package com.matariky.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SourcePermission {
    @AliasFor("oId")
    String value() default "";

    @AliasFor("value")
    String oId() default "";
}
