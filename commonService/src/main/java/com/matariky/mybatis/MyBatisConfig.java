package com.matariky.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.matariky.id.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyBatisConfig {

    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new SnowflakeIdGenerator(SnowflakeIdGenerator.getWorkerIdByIP());
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new MybatisPlusDataScopeInterceptor());
        interceptor.addInnerInterceptor(new MybatisPaginationInnerInterceptor());
        return interceptor;
    }

    @Bean
    @Primary
    public ISqlInjector sqlInjector() {
        return new MyBatisSqlInjector();
    }
}