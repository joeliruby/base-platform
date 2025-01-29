package com.matariky.commonservice.network.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.network.bean.BasicBaseNetworkLog;
import com.matariky.commonservice.network.service.BasicBaseNetworkLogService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseNetworkLogControllerTest {

    @InjectMocks
    private BasicBaseNetworkLogController basicBaseNetworkLogController;

    @Mock
    private BasicBaseNetworkLogService basicBaseNetworkLogService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogService.getBasicBaseNetworkLogAll(any())).thenReturn(Collections.singletonList(log));

        // When
        AjaxResult result = basicBaseNetworkLogController.list(request, log, "tenantId", 1, 10, "jwt");

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testExport() {
        // Given
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();

        // When
        basicBaseNetworkLogController.export(log);

        // Then
        verify(basicBaseNetworkLogService, times(1)).export(log);
    }

    @Test
    void testDaclist() {
        // Given
        when(request.getHeader("id")).thenReturn("12345");
        when(basicBaseNetworkLogService.getBasicBaseNetworkLogDAC(any(), any())).thenReturn(Collections.emptyList());
        when(basicBaseNetworkLogService.getBasicBaseNetworkLogDACCount(any(), any())).thenReturn(0L);

        // When
        AjaxResult result = basicBaseNetworkLogController.daclist(request, Collections.emptyMap(), "tenantId", "jwt");

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        List<BasicBaseNetworkLog> logs = Collections.singletonList(new BasicBaseNetworkLog());

        // When
        AjaxResult result = basicBaseNetworkLogController.save(logs, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        verify(basicBaseNetworkLogService, times(1)).createBasicBaseNetworkLogWithOrg(logs, request);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();

        // When
        AjaxResult result = basicBaseNetworkLogController.update(log, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        verify(basicBaseNetworkLogService, times(1)).updateBasicBaseNetworkLog(log);
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = basicBaseNetworkLogController.del(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        verify(basicBaseNetworkLogService, times(1)).deleteById(Long.parseLong(id));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogService.selectById(id)).thenReturn(log);

        // When
        AjaxResult result = basicBaseNetworkLogController.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(log, result.get(AjaxResult.DATA_TAG));
    }
}
