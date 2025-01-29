package com.matariky.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootTest
public class ClickHouseDataSourceConfigurationTest {

    @InjectMocks
    private ClickHouseDataSourceConfiguration clickhousedatasourceconfiguration;

    @Mock
    private DataSource dataSource;

    @Mock
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testClickDruidDataSource() {
        // Given
        // Initialize your test data and mocks here

        // When
        DataSource result = clickhousedatasourceconfiguration.clickDruidDataSource();

        // Then
        assertNotNull(result);
        assertTrue(result instanceof DruidDataSource);
    }

    @Test
    void testClickhouseSqlSessionFactory() throws Exception {
        // Given

        // When
        SqlSessionFactory result = clickhousedatasourceconfiguration.clickhouseSqlSessionFactory(dataSource);

        // Then
        assertNotNull(result);
    }

    @Test
    void testClickhouseTransactionManager() {
        // Given
        // Initialize your test data and mocks here

        // When
        DataSourceTransactionManager result = clickhousedatasourceconfiguration
                .clickhouseTransactionManager(dataSource);

        // Then
        assertNotNull(result);
        assertEquals(dataSource, result.getDataSource());
    }

    @Test
    void testClickhouseSqlSessionTemplate() throws Exception {
        // Given
        // Initialize your test data and mocks here

        // When
        SqlSessionTemplate result = clickhousedatasourceconfiguration.clickhouseSqlSessionTemplate(sqlSessionFactory);

        // Then
        assertNotNull(result);
        assertEquals(sqlSessionFactory, result.getSqlSessionFactory());
    }

    // Add more test methods for other methods in ClickHouseDataSourceConfiguration
}
