package com.matariky.commonservice.printlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;
import com.matariky.commonservice.printlog.service.BasicBasePrintLogService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBasePrintLogControllerTest {

    @InjectMocks
    private BasicBasePrintLogController basicBasePrintLogController;

    @Mock
    private BasicBasePrintLogService basicBasePrintLogService;

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
        BasicBasePrintLog bean = new BasicBasePrintLog();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        when(basicBasePrintLogService.getBasicBasePrintLogAll(bean)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicBasePrintLogController.list(request, bean, tenantId, pageIndex, perPage, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testExport() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();

        // When
        AjaxResult result = basicBasePrintLogController.export(bean);

        // Then
        verify(basicBasePrintLogService, times(1)).export(bean);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSave() {
        // Given
        List<BasicBasePrintLog> beanList = Collections.singletonList(new BasicBasePrintLog());

        // When
        AjaxResult result = basicBasePrintLogController.save(beanList, request, response);

        // Then
        verify(basicBasePrintLogService, times(1)).createBasicBasePrintLogWithOrg(beanList, request);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();

        // When
        AjaxResult result = basicBasePrintLogController.update(bean, request, response);

        // Then
        verify(basicBasePrintLogService, times(1)).updateBasicBasePrintLog(bean);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = basicBasePrintLogController.del(id, request, response);

        // Then
        verify(basicBasePrintLogService, times(1)).deleteById(Long.parseLong(id));
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBasePrintLog expectedLog = new BasicBasePrintLog();
        when(basicBasePrintLogService.selectById(id)).thenReturn(expectedLog);

        // When
        AjaxResult result = basicBasePrintLogController.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(expectedLog, result.get(AjaxResult.DATA_TAG));
    }
}
