package com.matariky.mybatis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.apache.ibatis.executor.Executor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;

@SpringBootTest
public class MybatisPaginationInnerInterceptorTest {

    @InjectMocks
    private MybatisPaginationInnerInterceptor mybatispaginationinnerinterceptor;

    @Mock
    private Executor executor;

    @Mock
    private Connection connection;

    @Mock
    private DatabaseMetaData metaData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindIDialectWithMySQL() throws Exception {
        // Given
        when(executor.getTransaction().getConnection()).thenReturn(connection);
        when(connection.getMetaData()).thenReturn(metaData);
        when(metaData.getURL()).thenReturn("jdbc:mysql://localhost:3306/test");

        // When
        var dialect = mybatispaginationinnerinterceptor.findIDialect(executor);

        // Then
        assertThat(dialect).isEqualTo(DialectFactory.getDialect(DbType.MYSQL));
    }

    @Test
    void testFindIDialectWithPostgreSQL() throws Exception {
        // Given
        when(executor.getTransaction().getConnection()).thenReturn(connection);
        when(connection.getMetaData()).thenReturn(metaData);
        when(metaData.getURL()).thenReturn("jdbc:postgresql://localhost:5432/test");

        // When
        var dialect = mybatispaginationinnerinterceptor.findIDialect(executor);

        // Then
        assertThat(dialect).isEqualTo(DialectFactory.getDialect(DbType.POSTGRE_SQL));
    }

    @Test
    void testFindIDialectWithException() throws Exception {
        // Given
        when(executor.getTransaction().getConnection()).thenThrow(new RuntimeException());

        // When
        var dialect = mybatispaginationinnerinterceptor.findIDialect(executor);

        // Then
        assertThat(dialect).isEqualTo(DialectFactory.getDialect(DbType.MYSQL));
    }

    // Add more test methods for other methods in MybatisPaginationInnerInterceptor
}
