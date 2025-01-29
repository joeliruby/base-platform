package com.matariky.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mockito.Mock;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@SpringBootTest
public class TdEngineDataSourceConfigTest {

    @InjectMocks
    private TdEngineDataSourceConfig tdengineDataSourceConfig;

    @Mock
    private DataSource dataSource;

    @Mock
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTdengineDataSource() {
        // Given
        // Initialize your test data and mocks here

        // When
        DataSource result = tdengineDataSourceConfig.tdengineDataSource();

        // Then
        assertNotNull(result);
    }

    @Test
    void testTdengineSqlSessionFactory() throws Exception {
        // Given
        // Initialize your test data and mocks here

        // When
        SqlSessionFactory result = tdengineDataSourceConfig.tdengineSqlSessionFactory(dataSource);

        // Then
        assertNotNull(result);
    }

    @Test
    void testTdengineTransactionManager() {
        // Given
        // Initialize your test data and mocks here

        // When
        DataSourceTransactionManager result = tdengineDataSourceConfig.tdengineTransactionManager(dataSource);

        // Then
        assertNotNull(result);
    }

    @Test
    void testTdengineSqlSessionTemplate() throws Exception {
        // Given
        // Initialize your test data and mocks here

        // When
        SqlSessionTemplate result = tdengineDataSourceConfig.tdengineSqlSessionTemplate(sqlSessionFactory);

        // Then
        assertNotNull(result);
    }

    // Add more test methods for other methods in TdEngineDataSourceConfig
}
