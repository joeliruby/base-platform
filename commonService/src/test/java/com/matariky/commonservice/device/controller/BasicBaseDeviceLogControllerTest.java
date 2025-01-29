package com.matariky.commonservice.device.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.device.bean.BasicBaseDeviceLog;
import com.matariky.commonservice.device.service.BasicBaseDeviceLogService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@SpringBootTest
public class BasicBaseDeviceLogControllerTest {

    @InjectMocks
    private BasicBaseDeviceLogController basicbasedevicelogcontroller;

    @Mock
    private BasicBaseDeviceLogService basicBaseDeviceLogService;

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
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        when(basicBaseDeviceLogService.getBasicBaseDeviceLogAll(bean)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicbasedevicelogcontroller.list(request, bean, tenantId, pageIndex, perPage, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.MSG_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testExport() {
        // Given
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();

        // When
        AjaxResult result = basicbasedevicelogcontroller.export(bean);

        // Then
        verify(basicBaseDeviceLogService, times(1)).export(bean);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();

        // When
        AjaxResult result = basicbasedevicelogcontroller.save(bean, request, response);

        // Then
        verify(basicBaseDeviceLogService, times(1)).createBasicBaseDeviceLogWithOrg(bean, request);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();

        // When
        AjaxResult result = basicbasedevicelogcontroller.update(bean, request, response);

        // Then
        verify(basicBaseDeviceLogService, times(1)).updateBasicBaseDeviceLog(bean);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = basicbasedevicelogcontroller.del(id, request, response);

        // Then
        verify(basicBaseDeviceLogService, times(1)).deleteById(Long.parseLong(id));
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDeviceLog log = new BasicBaseDeviceLog();
        when(basicBaseDeviceLogService.selectById(id)).thenReturn(log);

        // When
        AjaxResult result = basicbasedevicelogcontroller.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(log, result.get(AjaxResult.DATA_TAG));
    }

    // Add more test methods for other methods in BasicBaseDeviceLogController
}
