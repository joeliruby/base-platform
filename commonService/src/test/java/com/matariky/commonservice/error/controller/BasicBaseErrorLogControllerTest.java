package com.matariky.commonservice.error.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
import com.matariky.commonservice.error.service.BasicBaseErrorLogService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseErrorLogControllerTest {

    @InjectMocks
    private BasicBaseErrorLogController basicbaseerrorlogcontroller;

    @Mock
    private BasicBaseErrorLogService basicBaseErrorLogService;

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
        BasicBaseErrorLog bean = new BasicBaseErrorLog();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        when(basicBaseErrorLogService.getBasicBaseErrorLogAll(bean)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicbaseerrorlogcontroller.list(request, bean, tenantId, pageIndex, perPage, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testExport() {
        // Given
        BasicBaseErrorLog bean = new BasicBaseErrorLog();

        // When
        AjaxResult result = basicbaseerrorlogcontroller.export(bean);

        // Then
        verify(basicBaseErrorLogService, times(1)).export(bean);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSave() {
        // Given
        List<BasicBaseErrorLog> beanList = Collections.singletonList(new BasicBaseErrorLog());

        // When
        AjaxResult result = basicbaseerrorlogcontroller.save(beanList, request, response);

        // Then
        verify(basicBaseErrorLogService, times(1)).createBasicBaseErrorLogWithOrg(beanList, request);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseErrorLog bean = new BasicBaseErrorLog();

        // When
        AjaxResult result = basicbaseerrorlogcontroller.update(bean, request, response);

        // Then
        verify(basicBaseErrorLogService, times(1)).updateBasicBaseErrorLog(bean);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = basicbaseerrorlogcontroller.del(id, request, response);

        // Then
        verify(basicBaseErrorLogService, times(1)).deleteById(Long.parseLong(id));
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseErrorLog log = new BasicBaseErrorLog();
        when(basicBaseErrorLogService.selectById(id)).thenReturn(log);

        // When
        AjaxResult result = basicbaseerrorlogcontroller.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(log, result.get(AjaxResult.DATA_TAG));
    }
}
