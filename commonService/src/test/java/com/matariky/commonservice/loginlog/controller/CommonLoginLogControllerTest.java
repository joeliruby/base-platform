package com.matariky.commonservice.loginlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
import com.matariky.commonservice.loginlog.service.CommonLoginLogService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class CommonLoginLogControllerTest {

    @InjectMocks
    private CommonLoginLogController commonLoginLogController;

    @Mock
    private CommonLoginLogService commonLoginLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("index", "1");
        params.put("perPage", "10");
        String tenantId = "tenant1";
        String jwt = "jwtToken";

        // When
        AjaxResult result = (AjaxResult) commonLoginLogController.list(null, params, tenantId, jwt);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        CommonLoginLog commonLoginLog = new CommonLoginLog();
        when(commonLoginLogService.createCommonLoginLog(any(CommonLoginLog.class))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commonLoginLogController.save(commonLoginLog, null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        when(commonLoginLogService.selectById(id)).thenReturn(new CommonLoginLog());

        // When
        Object result = commonLoginLogController.getOne(id, null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof CommonLoginLog);
    }

    @Test
    void testUpdate() {
        // Given
        CommonLoginLog commonLoginLog = new CommonLoginLog();
        when(commonLoginLogService.updateCommonLoginLog(any(CommonLoginLog.class))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commonLoginLogController.update(commonLoginLog, null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(commonLoginLogService.delCommonLoginLogById(anyInt())).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commonLoginLogController.del(id, null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }
}
