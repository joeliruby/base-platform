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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootTest
public class MysqlDataSourceConfigurationTest {

    @InjectMocks
    private MysqlDataSourceConfiguration mysqldatasourceconfiguration;

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
        // When
        DataSource result = mysqldatasourceconfiguration.clickDruidDataSource();

        // Then
        assertNotNull(result);
        assertTrue(result instanceof DruidDataSource);
    }

    @Test
    void testJdbcTemplate() {
        // When
        JdbcTemplate jdbcTemplate = mysqldatasourceconfiguration.jdbcTemplate(dataSource);

        // Then
        assertNotNull(jdbcTemplate);
        assertEquals(dataSource, jdbcTemplate.getDataSource());
    }

    @Test
    void testMysqlTransactionManager() {
        // When
        DataSourceTransactionManager transactionManager = mysqldatasourceconfiguration
                .mysqlTransactionManager(dataSource);

        // Then
        assertNotNull(transactionManager);
        assertEquals(dataSource, transactionManager.getDataSource());
    }

    @Test
    void testMysqlSqlSessionTemplate() throws Exception {
        // When
        SqlSessionTemplate sqlSessionTemplate = mysqldatasourceconfiguration.mysqlSqlSessionTemplate(sqlSessionFactory);

        // Then
        assertNotNull(sqlSessionTemplate);
        assertEquals(sqlSessionFactory, sqlSessionTemplate.getSqlSessionFactory());
    }

    // Add more test methods for other methods in MysqlDataSourceConfiguration
}
