package com.matariky.clickhouse.logs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.ServiceLog;
import org.mockito.Mock;

@SpringBootTest
public class IServiceLogServiceTest {

    @InjectMocks
    private IServiceLogService iservicelogservice;

    @Mock
    private Page<ServiceLog> mockPage;

    @Mock
    private ServiceLog mockServiceLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTracesAll() {
        // Given
        when(iservicelogservice.getTracesAll(any(ServiceLog.class), anyInt(), anyInt())).thenReturn(mockPage);

        // When
        Page<ServiceLog> result = iservicelogservice.getTracesAll(mockServiceLog, 1, 10);

        // Then
        assertNotNull(result);
        verify(iservicelogservice).getTracesAll(mockServiceLog, 1, 10);
    }

    @Test
    void testGetAppTracesCount() {
        // Given
        Long expectedCount = 100L;
        when(iservicelogservice.getAppTracesCount(any(ServiceLog.class))).thenReturn(expectedCount);

        // When
        Long result = iservicelogservice.getAppTracesCount(mockServiceLog);

        // Then
        assertEquals(expectedCount, result);
        verify(iservicelogservice).getAppTracesCount(mockServiceLog);
    }

    @Test
    void testExport() {
        // Given
        doNothing().when(iservicelogservice).export(any(ServiceLog.class));

        // When
        iservicelogservice.export(mockServiceLog);

        // Then
        verify(iservicelogservice).export(mockServiceLog);
    }
}
