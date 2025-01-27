package com.matariky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = { "com.matariky.*.mapper" })
@ComponentScan(basePackages = { "com.matariky.*", "com.matariky.userservice.*", })
@EnableCaching
public class UserseriviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserseriviceApplication.class, args);
    }

}
