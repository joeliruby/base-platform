package com.matariky.commonservice.sqlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.sqlog.bean.CommonSqlLog;
import com.matariky.commonservice.sqlog.service.CommonSqlLogService;

@SpringBootTest
public class CommonSqlLogControllerTest {

    @InjectMocks
    private CommonSqlLogController commonsqllogcontroller;

    @Mock
    private CommonSqlLogService commonSqlLogService;

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
        CommonSqlLog bean = new CommonSqlLog();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        Page<CommonSqlLog> page = mock(Page.class);
        when(page.getResult()).thenReturn(new ArrayList<>());
        when(commonSqlLogService.getCommonSQLLogDynamicCondition(bean)).thenReturn(page);

        // When
        PageInfo<CommonSqlLog> result = commonsqllogcontroller.list(request, bean, tenantId, pageIndex, perPage, jwt);

        // Then
        assertNotNull(result);
        verify(commonSqlLogService).getCommonSQLLogDynamicCondition(bean);
    }

    @Test
    void testSave() {
        // Given
        CommonSqlLog bean = new CommonSqlLog();
        when(commonSqlLogService.createCommonSqlLog(bean)).thenReturn(1);

        // When
        ResponseEntity<String> responseEntity = commonsqllogcontroller.save(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("SUCCESS", responseEntity.getBody());
    }

    @Test
    void testUpdate() {
        // Given
        CommonSqlLog bean = new CommonSqlLog();
        when(commonSqlLogService.updateCommonSqlLog(bean)).thenReturn(1);

        // When
        ResponseEntity<String> responseEntity = commonsqllogcontroller.update(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("SUCCESS", responseEntity.getBody());
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(commonSqlLogService.delCommonSqlLogById(Integer.parseInt(id))).thenReturn(1);

        // When
        ResponseEntity<String> responseEntity = commonsqllogcontroller.del(id, request, response);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("SUCCESS", responseEntity.getBody());
    }
}
