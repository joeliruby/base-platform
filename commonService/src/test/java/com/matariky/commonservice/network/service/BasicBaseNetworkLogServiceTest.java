package com.matariky.commonservice.network.service;

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
import com.matariky.commonservice.network.bean.BasicBaseNetworkLog;
import com.matariky.commonservice.network.mapper.BasicBaseNetworkLogMapper;

@SpringBootTest
public class BasicBaseNetworkLogServiceTest {

    @InjectMocks
    private BasicBaseNetworkLogService basicBaseNetworkLogService;

    @Mock
    private BasicBaseNetworkLogMapper basicBaseNetworkLogMapper;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseNetworkLogAll() {
        // Given
        BasicBaseNetworkLog vo = new BasicBaseNetworkLog();
        List<BasicBaseNetworkLog> expectedList = Collections.singletonList(new BasicBaseNetworkLog());
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseNetworkLog> result = basicBaseNetworkLogService.getBasicBaseNetworkLogAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testExport() {
        // Given
        BasicBaseNetworkLog bean = new BasicBaseNetworkLog();
        List<BasicBaseNetworkLog> list = Collections.singletonList(new BasicBaseNetworkLog());
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogAll(bean)).thenReturn(list);

        // When
        basicBaseNetworkLogService.export(bean);

        // Then
        // Verify interactions and assertions
        verify(basicBaseNetworkLogMapper).getBasicBaseNetworkLogAll(bean);
    }

    @Test
    void testGetBasicBaseNetworkLogAllCount() {
        // Given
        int expectedCount = 10;
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBaseNetworkLogService.getBasicBaseNetworkLogAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseNetworkLog() {
        // Given
        BasicBaseNetworkLog bean = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.createBasicBaseNetworkLog(bean)).thenReturn(1);

        // When
        int result = basicBaseNetworkLogService.createBasicBaseNetworkLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseNetworkLog() {
        // Given
        BasicBaseNetworkLog bean = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseNetworkLogService.updateBasicBaseNetworkLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseNetworkLogById() {
        // Given
        int id = 1;
        when(basicBaseNetworkLogMapper.delBasicBaseNetworkLogById(id)).thenReturn(1);

        // When
        int result = basicBaseNetworkLogService.delBasicBaseNetworkLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseNetworkLogById() {
        // Given
        int id = 1;
        BasicBaseNetworkLog expectedLog = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogById(id)).thenReturn(expectedLog);

        // When
        BasicBaseNetworkLog result = basicBaseNetworkLogService.getBasicBaseNetworkLogById(id);

        // Then
        assertEquals(expectedLog, result);
    }
}
