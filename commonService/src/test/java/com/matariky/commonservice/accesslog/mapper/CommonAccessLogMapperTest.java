package com.matariky.commonservice.accesslog.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.commonservice.accesslog.bean.CommonAccessLog;

@SpringBootTest
public class CommonAccessLogMapperTest {

    @InjectMocks
    private CommonAccessLogMapper commonAccessLogMapper;

    @Mock
    private CommonAccessLogMapper mockCommonAccessLogMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonAccessLogAll() {
        // Given
        Page<CommonAccessLog> expectedPage = new Page<>();
        when(mockCommonAccessLogMapper.getCommonAccessLogAll()).thenReturn(expectedPage);

        // When
        Page<CommonAccessLog> result = commonAccessLogMapper.getCommonAccessLogAll();

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateCommonAccessLog() {
        // Given
        CommonAccessLog log = new CommonAccessLog();
        when(mockCommonAccessLogMapper.createCommonAccessLog(log)).thenReturn(1);

        // When
        int result = commonAccessLogMapper.createCommonAccessLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonAccessLog() {
        // Given
        CommonAccessLog log = new CommonAccessLog();
        when(mockCommonAccessLogMapper.updateCommonAccessLog(log)).thenReturn(1);

        // When
        int result = commonAccessLogMapper.updateCommonAccessLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonAccessLogById() {
        // Given
        int id = 1;
        when(mockCommonAccessLogMapper.delCommonAccessLogById(id)).thenReturn(1);

        // When
        int result = commonAccessLogMapper.delCommonAccessLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonAccessLogById() {
        // Given
        String id = "1";
        CommonAccessLog expectedLog = new CommonAccessLog();
        when(mockCommonAccessLogMapper.getCommonAccessLogById(id)).thenReturn(expectedLog);

        // When
        CommonAccessLog result = commonAccessLogMapper.getCommonAccessLogById(id);

        // Then
        assertEquals(expectedLog, result);
    }

    @Test
    void testGetCommonAccessLogDynamicCondition() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<CommonAccessLog> expectedLogs = Collections.emptyList();
        when(mockCommonAccessLogMapper.getCommonAccessLogDynamicCondition(params)).thenReturn(expectedLogs);

        // When
        List<CommonAccessLog> result = commonAccessLogMapper.getCommonAccessLogDynamicCondition(params);

        // Then
        assertEquals(expectedLogs, result);
    }

    @Test
    void testGetCommonAccessLogDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<CommonAccessLog> expectedLogs = Collections.emptyList();
        when(mockCommonAccessLogMapper.getCommonAccessLogDAC(params)).thenReturn(expectedLogs);

        // When
        List<CommonAccessLog> result = commonAccessLogMapper.getCommonAccessLogDAC(params);

        // Then
        assertEquals(expectedLogs, result);
    }

    @Test
    void testGetCommonAccessLogDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 1L;
        when(mockCommonAccessLogMapper.getCommonAccessLogDACCount(params)).thenReturn(expectedCount);

        // When
        Long result = commonAccessLogMapper.getCommonAccessLogDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testGetCommonAccessLogByIds() {
        // Given
        String[] ids = { "1", "2" };
        List<CommonAccessLog> expectedLogs = Collections.emptyList();
        when(mockCommonAccessLogMapper.getCommonAccessLogByIds(ids)).thenReturn(expectedLogs);

        // When
        List<CommonAccessLog> result = commonAccessLogMapper.getCommonAccessLogByIds(ids);

        // Then
        assertEquals(expectedLogs, result);
    }
}
