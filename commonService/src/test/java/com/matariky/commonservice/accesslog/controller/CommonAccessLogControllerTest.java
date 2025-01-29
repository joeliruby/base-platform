package com.matariky.commonservice.accesslog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.accesslog.bean.CommonAccessLog;
import com.matariky.commonservice.accesslog.service.CommonAccessLogService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.redis.RedisUtils;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class CommonAccessLogControllerTest {

    @InjectMocks
    private CommonAccessLogController commonAccessLogController;

    @Mock
    private CommonAccessLogService commonAccessLogService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private RedisUtils redisUtils;

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
        Map<String, Object> params = new HashMap<>();
        params.put("index", "1");
        params.put("perPage", "10");
        String tenantId = "tenant1";
        String jwt = "jwtToken";

        // When
        AjaxResult result = (AjaxResult) commonAccessLogController.list(request, params, tenantId, jwt);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDaclist() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("index", "1");
        params.put("perPage", "10");
        String tenantId = "tenant1";
        String jwt = "jwtToken";

        // When
        AjaxResult result = (AjaxResult) commonAccessLogController.daclist(request, params, tenantId, jwt);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        String commonAccessLogId = "log1";

        // When
        AjaxResult result = (AjaxResult) commonAccessLogController.getOne(commonAccessLogId, request, response);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        CommonAccessLog log = new CommonAccessLog();

        // When
        AjaxResult result = (AjaxResult) commonAccessLogController.save(log, request, response);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        CommonAccessLog log = new CommonAccessLog();

        // When
        AjaxResult result = (AjaxResult) commonAccessLogController.update(log, request, response);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDel() {
        // Given
        String id = "1";

        // When
        AjaxResult result = (AjaxResult) commonAccessLogController.del(id, request, response);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }
}
