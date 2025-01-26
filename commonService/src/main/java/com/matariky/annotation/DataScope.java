package com.matariky.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    @AliasFor("alias")
    String value() default "t";

    @AliasFor("value")
    String alias() default "t";

    String paramName() default "params";
}
