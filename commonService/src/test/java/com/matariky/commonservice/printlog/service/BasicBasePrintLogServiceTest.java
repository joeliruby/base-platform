package com.matariky.commonservice.printlog.service;

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
import org.mockito.Mock;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;
import com.matariky.commonservice.printlog.mapper.BasicBasePrintLogMapper;

@SpringBootTest
public class BasicBasePrintLogServiceTest {

    @InjectMocks
    private BasicBasePrintLogService basicBasePrintLogService;

    @Mock
    private BasicBasePrintLogMapper basicBasePrintLogMapper;

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
    void testGetBasicBasePrintLogAll() {
        // Given
        BasicBasePrintLog vo = new BasicBasePrintLog();
        List<BasicBasePrintLog> expectedList = Collections.singletonList(new BasicBasePrintLog());
        when(basicBasePrintLogMapper.getBasicBasePrintLogAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBasePrintLog> result = basicBasePrintLogService.getBasicBasePrintLogAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testExport() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();
        List<BasicBasePrintLog> list = Collections.singletonList(bean);
        when(basicBasePrintLogMapper.getBasicBasePrintLogAll(bean)).thenReturn(list);

        // When
        basicBasePrintLogService.export(bean);

        // Then
        // Verify interactions and assertions
        verify(response).setContentType("application/vnd.ms-excel; charset=utf-8");
        verify(response).setCharacterEncoding("utf-8");
    }

    @Test
    void testGetBasicBasePrintLogAllCount() {
        // Given
        int expectedCount = 10;
        when(basicBasePrintLogMapper.getBasicBasePrintLogAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBasePrintLogService.getBasicBasePrintLogAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBasePrintLog() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();
        when(basicBasePrintLogMapper.createBasicBasePrintLog(bean)).thenReturn(1);

        // When
        int result = basicBasePrintLogService.createBasicBasePrintLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBasePrintLog() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();
        when(basicBasePrintLogMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBasePrintLogService.updateBasicBasePrintLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBasePrintLogById() {
        // Given
        int id = 1;
        when(basicBasePrintLogMapper.delBasicBasePrintLogById(id)).thenReturn(1);

        // When
        int result = basicBasePrintLogService.delBasicBasePrintLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBasePrintLogById() {
        // Given
        int id = 1;
        BasicBasePrintLog expectedLog = new BasicBasePrintLog();
        when(basicBasePrintLogMapper.getBasicBasePrintLogById(id)).thenReturn(expectedLog);

        // When
        BasicBasePrintLog result = basicBasePrintLogService.getBasicBasePrintLogById(id);

        // Then
        assertEquals(expectedLog, result);
    }
}
