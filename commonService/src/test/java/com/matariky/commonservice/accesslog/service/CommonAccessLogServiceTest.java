package com.matariky.commonservice.accesslog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.commonservice.accesslog.bean.CommonAccessLog;
import com.matariky.commonservice.accesslog.mapper.CommonAccessLogMapper;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class CommonAccessLogServiceTest {

    @InjectMocks
    private CommonAccessLogService commonAccessLogService;

    @Mock
    private CommonAccessLogMapper commonAccessLogMapper;

    @Mock
    private CommonSharingPoolService sharingPoolService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonAccessLogAll() {
        // Given
        Page<CommonAccessLog> expectedPage = mock(Page.class);
        when(commonAccessLogMapper.getCommonAccessLogAll()).thenReturn(expectedPage);

        // When
        Page<CommonAccessLog> result = commonAccessLogService.getCommonAccessLogAll();

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateCommonAccessLog() {
        // Given
        CommonAccessLog log = new CommonAccessLog();
        when(commonAccessLogMapper.createCommonAccessLog(log)).thenReturn(1);

        // When
        int result = commonAccessLogService.createCommonAccessLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonAccessLog() {
        // Given
        CommonAccessLog log = new CommonAccessLog();
        when(commonAccessLogMapper.updateCommonAccessLog(log)).thenReturn(1);

        // When
        int result = commonAccessLogService.updateCommonAccessLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonAccessLogById() {
        // Given
        int id = 1;
        when(commonAccessLogMapper.delCommonAccessLogById(id)).thenReturn(1);

        // When
        int result = commonAccessLogService.delCommonAccessLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonAccessLogById() {
        // Given
        String id = "1";
        CommonAccessLog expectedLog = new CommonAccessLog();
        when(commonAccessLogMapper.getCommonAccessLogById(id)).thenReturn(expectedLog);

        // When
        CommonAccessLog result = commonAccessLogService.getCommonAccessLogById(id);

        // Then
        assertEquals(expectedLog, result);
    }

    @Test
    void testGetCommonAccessLogDynamicCondition() {
        // Given
        Map<String, Object> params = new HashMap<>();
        List<CommonAccessLog> expectedLogs = mock(List.class);
        when(commonAccessLogMapper.getCommonAccessLogDynamicCondition(params)).thenReturn(expectedLogs);

        // When
        List<CommonAccessLog> result = commonAccessLogService.getCommonAccessLogDynamicCondition(params);

        // Then
        assertEquals(expectedLogs, result);
    }

    @Test
    void testGetCommonAccessLogDAC() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        List<CommonAccessLog> expectedLogs = mock(List.class);
        when(commonAccessLogMapper.getCommonAccessLogDAC(params)).thenReturn(expectedLogs);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");

        // When
        List<CommonAccessLog> result = commonAccessLogService.getCommonAccessLogDAC(params, request);

        // Then
        assertEquals(expectedLogs, result);
    }

    @Test
    void testGetCommonAccessLogDACCount() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        Long expectedCount = 10L;
        when(commonAccessLogMapper.getCommonAccessLogDACCount(params)).thenReturn(expectedCount);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");

        // When
        Long result = commonAccessLogService.getCommonAccessLogDACCount(params, request);

        // Then
        assertEquals(expectedCount, result);
    }
}
