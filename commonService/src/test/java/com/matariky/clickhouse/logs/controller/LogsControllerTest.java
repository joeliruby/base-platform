package com.matariky.clickhouse.logs.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.github.pagehelper.PageInfo;
import com.matariky.clickhouse.logs.entity.Logs;
import com.matariky.clickhouse.logs.service.ILogsService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class LogsControllerTest {

    @InjectMocks
    private LogsController logsController;

    @Mock
    private ILogsService logsService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        Logs logs = new Logs();
        when(request.getParameter("perPage")).thenReturn("10");
        when(request.getParameter("index")).thenReturn("1");
        when(logsService.getAppTracesCount(logs)).thenReturn(100L);
        when(logsService.getTracesAll(logs, 1, 10)).thenReturn(new Page<Logs>());

        // When
        AjaxResult result = logsController.list(request, logs);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isInstanceOf(PageInfo.class);
    }

    @Test
    void testExport() {
        // Given
        Logs logs = new Logs();
        when(request.getParameter("perPage")).thenReturn("10");
        when(request.getParameter("index")).thenReturn("1");

        // When
        AjaxResult result = logsController.export(request, logs);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(logsService, times(1)).export(logs, 1, 10);
    }

    // Add more test methods for other methods in LogsController
}
