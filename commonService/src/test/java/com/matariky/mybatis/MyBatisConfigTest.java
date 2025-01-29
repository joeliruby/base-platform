package com.matariky.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.matariky.id.SnowflakeIdGenerator;

@SpringBootTest
public class MyBatisConfigTest {

    @InjectMocks
    private MyBatisConfig mybatisconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIdentifierGenerator() {
        // Given
        SnowflakeIdGenerator expectedGenerator = new SnowflakeIdGenerator(SnowflakeIdGenerator.getWorkerIdByIP());

        // When
        IdentifierGenerator identifierGenerator = mybatisconfig.identifierGenerator();

        // Then
        assertThat(identifierGenerator).isInstanceOf(SnowflakeIdGenerator.class);
        assertThat(identifierGenerator).isEqualToComparingFieldByField(expectedGenerator);
    }

    @Test
    void testMybatisPlusInterceptor() {
        // When
        MybatisPlusInterceptor interceptor = mybatisconfig.mybatisPlusInterceptor();

        // Then
        assertThat(interceptor).isNotNull();
        assertThat(interceptor.getInterceptors()).hasSize(2);
    }

    @Test
    void testSqlInjector() {
        // When
        ISqlInjector sqlInjector = mybatisconfig.sqlInjector();

        // Then
        assertThat(sqlInjector).isInstanceOf(MyBatisSqlInjector.class);
    }
}
