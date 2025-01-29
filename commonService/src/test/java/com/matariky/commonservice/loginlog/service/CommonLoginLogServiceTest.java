package com.matariky.commonservice.loginlog.service;

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
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
import com.matariky.commonservice.loginlog.mapper.CommonLoginLogMapper;

@SpringBootTest
public class CommonLoginLogServiceTest {

    @InjectMocks
    private CommonLoginLogService commonLoginLogService;

    @Mock
    private CommonLoginLogMapper commonLoginLogMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonLoginLogAll() {
        // Given
        Page<CommonLoginLog> expectedPage = mock(Page.class);
        when(commonLoginLogMapper.getCommonLoginLogAll()).thenReturn(expectedPage);

        // When
        Page<CommonLoginLog> result = commonLoginLogService.getCommonLoginLogAll();

        // Then
        assertEquals(expectedPage, result);
        verify(commonLoginLogMapper).getCommonLoginLogAll();
    }

    @Test
    void testGetCommonLoginLogAllCount() {
        // Given
        int expectedCount = 10;
        when(commonLoginLogMapper.getCommonLoginLogAllCount()).thenReturn(expectedCount);

        // When
        int result = commonLoginLogService.getCommonLoginLogAllCount();

        // Then
        assertEquals(expectedCount, result);
        verify(commonLoginLogMapper).getCommonLoginLogAllCount();
    }

    @Test
    void testCreateCommonLoginLog() {
        // Given
        CommonLoginLog log = new CommonLoginLog();
        when(commonLoginLogMapper.createCommonLoginLog(log)).thenReturn(1);

        // When
        int result = commonLoginLogService.createCommonLoginLog(log);

        // Then
        assertEquals(1, result);
        verify(commonLoginLogMapper).createCommonLoginLog(log);
    }

    @Test
    void testUpdateCommonLoginLog() {
        // Given
        CommonLoginLog log = new CommonLoginLog();
        when(commonLoginLogMapper.updateCommonLoginLog(log)).thenReturn(1);

        // When
        int result = commonLoginLogService.updateCommonLoginLog(log);

        // Then
        assertEquals(1, result);
        verify(commonLoginLogMapper).updateCommonLoginLog(log);
    }

    @Test
    void testDelCommonLoginLogById() {
        // Given
        int id = 1;
        when(commonLoginLogMapper.delCommonLoginLogById(id)).thenReturn(1);

        // When
        int result = commonLoginLogService.delCommonLoginLogById(id);

        // Then
        assertEquals(1, result);
        verify(commonLoginLogMapper).delCommonLoginLogById(id);
    }

    @Test
    void testGetCommonLoginLogById() {
        // Given
        int id = 1;
        CommonLoginLog expectedLog = new CommonLoginLog();
        when(commonLoginLogMapper.getCommonLoginLogById(id)).thenReturn(expectedLog);

        // When
        CommonLoginLog result = commonLoginLogService.getCommonLoginLogById(id);

        // Then
        assertEquals(expectedLog, result);
        verify(commonLoginLogMapper).getCommonLoginLogById(id);
    }

    @Test
    void testGetCommonLoginLogDynamicCondition() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<CommonLoginLog> expectedLogs = Collections.emptyList();
        when(commonLoginLogMapper.getCommonLoginLogDynamicCondition(params)).thenReturn(expectedLogs);

        // When
        List<CommonLoginLog> result = commonLoginLogService.getCommonLoginLogDynamicCondition(params);

        // Then
        assertEquals(expectedLogs, result);
        verify(commonLoginLogMapper).getCommonLoginLogDynamicCondition(params);
    }

    @Test
    void testGetCommonLoginLogByIds() {
        // Given
        String[] ids = { "1", "2" };
        List<CommonLoginLog> expectedLogs = Collections.emptyList();
        when(commonLoginLogMapper.getCommonLoginLogByIds(ids)).thenReturn(expectedLogs);

        // When
        List<CommonLoginLog> result = commonLoginLogService.getCommonLoginLogByIds(ids);

        // Then
        assertEquals(expectedLogs, result);
        verify(commonLoginLogMapper).getCommonLoginLogByIds(ids);
    }
}
