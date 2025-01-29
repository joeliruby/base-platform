package com.matariky.clickhouse.logs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.ServiceLog;
import com.matariky.clickhouse.logs.service.IServiceLogService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class ServiceLogControllerTest {

    @InjectMocks
    private ServiceLogController servicelogcontroller;

    @Mock
    private IServiceLogService logsService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        when(request.getParameter("perPage")).thenReturn("10");
        when(request.getParameter("index")).thenReturn("1");
        ServiceLog logs = new ServiceLog();
        logs.setTimeStart("1633046400000"); // Example timestamp
        logs.setTimeEnd("1633132800000"); // Example timestamp

        when(logsService.getAppTracesCount(any())).thenReturn(100L);
        when(logsService.getTracesAll(any(), anyInt(), anyInt())).thenReturn(new Page<>());

        // When
        AjaxResult result = servicelogcontroller.list(request, logs);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testExport() {
        // Given
        ServiceLog logs = new ServiceLog();
        logs.setTimeStart("1633046400000"); // Example timestamp
        logs.setTimeEnd("1633132800000"); // Example timestamp

        // When
        AjaxResult result = servicelogcontroller.export(logs);

        // Then
        verify(logsService, times(1)).export(logs);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    // Add more test methods for other methods in ServiceLogController
}
