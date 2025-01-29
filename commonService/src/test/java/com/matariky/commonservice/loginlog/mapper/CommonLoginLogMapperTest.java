package com.matariky.commonservice.loginlog.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;

@SpringBootTest
public class CommonLoginLogMapperTest {

    @InjectMocks
    private CommonLoginLogMapper commonloginlogmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonLoginLogAll() {
        // Given
        Page<CommonLoginLog> expectedPage = new Page<>();
        when(commonloginlogmapper.getCommonLoginLogAll()).thenReturn(expectedPage);

        // When
        Page<CommonLoginLog> result = commonloginlogmapper.getCommonLoginLogAll();

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateCommonLoginLog() {
        // Given
        CommonLoginLog log = new CommonLoginLog();
        when(commonloginlogmapper.createCommonLoginLog(log)).thenReturn(1);

        // When
        int result = commonloginlogmapper.createCommonLoginLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonLoginLog() {
        // Given
        CommonLoginLog log = new CommonLoginLog();
        when(commonloginlogmapper.updateCommonLoginLog(log)).thenReturn(1);

        // When
        int result = commonloginlogmapper.updateCommonLoginLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonLoginLogById() {
        // Given
        int id = 1;
        when(commonloginlogmapper.delCommonLoginLogById(id)).thenReturn(1);

        // When
        int result = commonloginlogmapper.delCommonLoginLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonLoginLogById() {
        // Given
        int id = 1;
        CommonLoginLog expectedLog = new CommonLoginLog();
        when(commonloginlogmapper.getCommonLoginLogById(id)).thenReturn(expectedLog);

        // When
        CommonLoginLog result = commonloginlogmapper.getCommonLoginLogById(id);

        // Then
        assertEquals(expectedLog, result);
    }

    @Test
    void testGetCommonLoginLogByIds() {
        // Given
        String[] ids = { "1", "2" };
        List<CommonLoginLog> expectedLogs = Collections.emptyList();
        when(commonloginlogmapper.getCommonLoginLogByIds(ids)).thenReturn(expectedLogs);

        // When
        List<CommonLoginLog> result = commonloginlogmapper.getCommonLoginLogByIds(ids);

        // Then
        assertEquals(expectedLogs, result);
    }
}
