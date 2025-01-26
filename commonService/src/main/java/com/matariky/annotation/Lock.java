package com.matariky.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {

    @AliasFor("key")
    String value() default "";

    @AliasFor("value")
    String key() default "";

    String keyMethod() default "";

}
