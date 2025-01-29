package com.matariky.commonservice.sqlog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.pagehelper.Page;
import com.matariky.commonservice.sqlog.bean.CommonSqlLog;
import com.matariky.commonservice.sqlog.mapper.CommonSqlLogMapper;
import org.mockito.Mock;

@SpringBootTest
public class CommonSqlLogServiceTest {

    @InjectMocks
    private CommonSqlLogService commonsqllogservice;

    @Mock
    private CommonSqlLogMapper commonSqlLogMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonSqlLogAll() {
        // Given
        Page<CommonSqlLog> expectedPage = mock(Page.class);
        when(commonSqlLogMapper.getCommonSqlLogAll()).thenReturn(expectedPage);

        // When
        Page<CommonSqlLog> result = commonsqllogservice.getCommonSqlLogAll();

        // Then
        assertThat(result).isEqualTo(expectedPage);
    }

    @Test
    void testGetCommonSqlLogAllCount() {
        // Given
        int expectedCount = 10;
        when(commonSqlLogMapper.getCommonSqlLogAllCount()).thenReturn(expectedCount);

        // When
        int result = commonsqllogservice.getCommonSqlLogAllCount();

        // Then
        assertThat(result).isEqualTo(expectedCount);
    }

    @Test
    void testCreateCommonSqlLog() {
        // Given
        CommonSqlLog log = new CommonSqlLog();
        when(commonSqlLogMapper.createCommonSqlLog(log)).thenReturn(1);

        // When
        int result = commonsqllogservice.createCommonSqlLog(log);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testUpdateCommonSqlLog() {
        // Given
        CommonSqlLog log = new CommonSqlLog();
        when(commonSqlLogMapper.updateCommonSqlLog(log)).thenReturn(1);

        // When
        int result = commonsqllogservice.updateCommonSqlLog(log);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDelCommonSqlLogById() {
        // Given
        int id = 1;
        when(commonSqlLogMapper.delCommonSqlLogById(id)).thenReturn(1);

        // When
        int result = commonsqllogservice.delCommonSqlLogById(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetCommonSqlLogById() {
        // Given
        int id = 1;
        CommonSqlLog expectedLog = new CommonSqlLog();
        when(commonSqlLogMapper.getCommonSqlLogById(id)).thenReturn(expectedLog);

        // When
        CommonSqlLog result = commonsqllogservice.getCommonSqlLogById(id);

        // Then
        assertThat(result).isEqualTo(expectedLog);
    }

    @Test
    void testGetCommonSQLLogDynamicCondition() {
        // Given
        CommonSqlLog log = new CommonSqlLog();
        Page<CommonSqlLog> expectedPage = mock(Page.class);
        when(commonSqlLogMapper.getCommonSQLLogDynamicCondition(log)).thenReturn(expectedPage);

        // When
        Page<CommonSqlLog> result = commonsqllogservice.getCommonSQLLogDynamicCondition(log);

        // Then
        assertThat(result).isEqualTo(expectedPage);
    }
}
