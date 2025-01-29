package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlUtilsTest {

    @InjectMocks
    private SqlUtils sqlutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMybatisSql() throws IllegalAccessException {
        // Given
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        SqlSessionFactory sqlSessionFactory = mock(SqlSessionFactory.class);
        // Initialize your test data and mocks here

        // When
        String result = SqlUtils.getMybatisSql(pjp, sqlSessionFactory);

        // Then
        // Assert the expected results
        assertNotNull(result);
    }

    @Test
    void testShowSql() {
        // Given
        Configuration configuration = mock(Configuration.class);
        BoundSql boundSql = mock(BoundSql.class);
        // Initialize your test data and mocks here

        // When
        String result = SqlUtils.showSql(configuration, boundSql);

        // Then
        // Assert the expected results
        assertNotNull(result);
    }

    @Test
    void testGetParameterValue() {
        // Given
        Object obj = "test";

        // When
        String result = SqlUtils.getParameterValue(obj);

        // Then
        // Assert the expected results
        assertEquals("'test'", result);
    }

    @Test
    void testObjectToMap() throws IllegalAccessException {
        // Given
        Object obj = new Object(); // Replace with an actual object

        // When
        Map<String, String> result = SqlUtils.objectToMap(obj);

        // Then
        // Assert the expected results
        assertNotNull(result);
    }

    // Add more test methods for other methods in SqlUtils
}
