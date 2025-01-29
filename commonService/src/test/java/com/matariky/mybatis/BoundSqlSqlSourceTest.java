package com.matariky.mybatis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.apache.ibatis.mapping.BoundSql;
import org.mockito.Mock;

@SpringBootTest
public class BoundSqlSqlSourceTest {

    @InjectMocks
    private BoundSqlSqlSource boundsqlsqlsource;

    @Mock
    private BoundSql mockBoundSql;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        boundsqlsqlsource = new BoundSqlSqlSource(mockBoundSql);
    }

    @Test
    void testGetBoundSql() {
        // Given
        Object parameterObject = new Object();

        // When
        BoundSql result = boundsqlsqlsource.getBoundSql(parameterObject);

        // Then
        assertThat(result).isEqualTo(mockBoundSql);
    }

    @Test
    void testBoundSqlNotNull() {
        // Given
        // BoundSql is already initialized in setUp()

        // When
        BoundSql result = boundsqlsqlsource.getBoundSql(new Object());

        // Then
        assertNotNull(result);
    }

    @Test
    void testBoundSqlEquality() {
        // Given
        BoundSql anotherBoundSql = mock(BoundSql.class);
        BoundSqlSqlSource anotherBoundSqlSqlSource = new BoundSqlSqlSource(anotherBoundSql);

        // When
        BoundSql result = anotherBoundSqlSqlSource.getBoundSql(new Object());

        // Then
        assertThat(result).isEqualTo(anotherBoundSql);
    }
}
