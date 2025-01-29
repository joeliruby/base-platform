package com.matariky.commonservice.error.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
import com.matariky.commonservice.error.mapper.BasicBaseErrorLogMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import org.mockito.Mock;

@SpringBootTest
public class BasicBaseErrorLogServiceTest {

    @InjectMocks
    private BasicBaseErrorLogService basicBaseErrorLogService;

    @Mock
    private BasicBaseErrorLogMapper basicBaseErrorLogMapper;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private CommonDictService commonDictService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseErrorLogAll() {
        // Given
        BasicBaseErrorLog vo = new BasicBaseErrorLog();
        List<BasicBaseErrorLog> expectedList = Collections.singletonList(new BasicBaseErrorLog());
        when(basicBaseErrorLogMapper.getBasicBaseErrorLogAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseErrorLog> result = basicBaseErrorLogService.getBasicBaseErrorLogAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testExport() {
        // Given
        BasicBaseErrorLog bean = new BasicBaseErrorLog();
        List<BasicBaseErrorLog> list = Collections.singletonList(new BasicBaseErrorLog());
        when(basicBaseErrorLogMapper.getBasicBaseErrorLogAll(bean)).thenReturn(list);

        // When
        basicBaseErrorLogService.export(bean);

        // Then
        // Verify that the response headers and content type are set correctly
        verify(response).setContentType("application/vnd.ms-excel; charset=utf-8");
        verify(response).setCharacterEncoding("utf-8");
    }

    @Test
    void testGetBasicBaseErrorLogAllCount() {
        // Given
        int expectedCount = 10;
        when(basicBaseErrorLogMapper.getBasicBaseErrorLogAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBaseErrorLogService.getBasicBaseErrorLogAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseErrorLog() {
        // Given
        BasicBaseErrorLog bean = new BasicBaseErrorLog();
        when(basicBaseErrorLogMapper.createBasicBaseErrorLog(bean)).thenReturn(1);

        // When
        int result = basicBaseErrorLogService.createBasicBaseErrorLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseErrorLog() {
        // Given
        BasicBaseErrorLog bean = new BasicBaseErrorLog();
        when(basicBaseErrorLogMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseErrorLogService.updateBasicBaseErrorLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseErrorLogById() {
        // Given
        int id = 1;
        when(basicBaseErrorLogMapper.delBasicBaseErrorLogById(id)).thenReturn(1);

        // When
        int result = basicBaseErrorLogService.delBasicBaseErrorLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseErrorLogById() {
        // Given
        int id = 1;
        BasicBaseErrorLog expectedLog = new BasicBaseErrorLog();
        when(basicBaseErrorLogMapper.getBasicBaseErrorLogById(id)).thenReturn(expectedLog);

        // When
        BasicBaseErrorLog result = basicBaseErrorLogService.getBasicBaseErrorLogById(id);

        // Then
        assertEquals(expectedLog, result);
    }
}
