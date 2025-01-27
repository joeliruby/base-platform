package com.matariky.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.matariky.id.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @description: Mybatis configuration class
 * @author: bo.chen
 * @create: 2023/8/25 16:56
 **/
@Configuration
public class MyBatisConfig {

    /**
     * @Description: 声明 Data 范围拦截器
     * @Author: bo.chen
     * @Date: 2023/10/9 16:31
     * @return com.matariky.mybatis.DataScopeInterceptor
     **/
    /*
     * @Bean
     * public DataScopeInterceptor dataIsolationInterceptor() {
     * return new DataScopeInterceptor();
     * }
     */

    /**
     * @Description: 声明id
     * @Author: bo.chen
     * @Date: 2023/10/9 16:31
     * @return com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator
     **/
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new SnowflakeIdGenerator(SnowflakeIdGenerator.getWorkerIdByIP());
    }

    /**
     * @Description: Configuration Pagination 拦截器
     * @Author: bo.chen
     * @Date: 2023/1/3 16:57
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new MybatisPlusDataScopeInterceptor());
        interceptor.addInnerInterceptor(new MybatisPaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * @Description: Declare SQL injector
     * @Author: bo.chen
     * @Date: 2023/8/25 16:21
     * @return com.baomidou.mybatisplus.core.injector.ISqlInjector
     **/
    @Bean
    @Primary
    public ISqlInjector sqlInjector() {
        return new MyBatisSqlInjector();
    }
}