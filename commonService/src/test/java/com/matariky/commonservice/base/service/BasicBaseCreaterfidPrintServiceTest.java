package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseCreaterfidPrint;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidPrintMapper;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BasicBaseCreaterfidPrintServiceTest {

    @InjectMocks
    private BasicBaseCreaterfidPrintService basicBaseCreaterfidPrintService;

    @Mock
    private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintAll() {
        // Given
        int pageIndex = 1;
        int perPage = 10;
        List<BasicBaseCreaterfidPrint> expectedList = Collections.singletonList(new BasicBaseCreaterfidPrint());
        when(basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintAll()).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidPrint> result = basicBaseCreaterfidPrintService
                .getBasicBaseCreaterfidPrintAll(pageIndex, perPage);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintAllCount() {
        // Given
        int expectedCount = 5;
        when(basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBaseCreaterfidPrintService.getBasicBaseCreaterfidPrintAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseCreaterfidPrint() {
        // Given
        BasicBaseCreaterfidPrint bean = new BasicBaseCreaterfidPrint();
        when(basicBaseCreaterfidPrintMapper.createBasicBaseCreaterfidPrint(bean)).thenReturn(1);

        // When
        int result = basicBaseCreaterfidPrintService.createBasicBaseCreaterfidPrint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testCreateBasicBaseCreaterfidPrintWithOrg() {
        // Given
        BasicBaseCreaterfidPrint bean = new BasicBaseCreaterfidPrint();
        when(request.getHeader("Authorization")).thenReturn("token");
        when(basicBaseCreaterfidPrintMapper.createBasicBaseCreaterfidPrint(bean)).thenReturn(1);

        // When
        int result = basicBaseCreaterfidPrintService.createBasicBaseCreaterfidPrintWithOrg(bean, request);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseCreaterfidPrint() {
        // Given
        BasicBaseCreaterfidPrint bean = new BasicBaseCreaterfidPrint();
        when(basicBaseCreaterfidPrintMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseCreaterfidPrintService.updateBasicBaseCreaterfidPrint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseCreaterfidPrintById() {
        // Given
        int id = 1;
        when(basicBaseCreaterfidPrintMapper.delBasicBaseCreaterfidPrintById(id)).thenReturn(1);

        // When
        int result = basicBaseCreaterfidPrintService.delBasicBaseCreaterfidPrintById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintById() {
        // Given
        int id = 1;
        BasicBaseCreaterfidPrint expectedBean = new BasicBaseCreaterfidPrint();
        when(basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintById(id)).thenReturn(expectedBean);

        // When
        BasicBaseCreaterfidPrint result = basicBaseCreaterfidPrintService.getBasicBaseCreaterfidPrintById(id);

        // Then
        assertEquals(expectedBean, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseCreaterfidPrint> expectedList = Collections.singletonList(new BasicBaseCreaterfidPrint());
        when(basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidPrint> result = basicBaseCreaterfidPrintService.getBasicBaseCreaterfidPrintDAC(params,
                request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        long expectedCount = 5L;
        when(basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintDACCount(params)).thenReturn(expectedCount);

        // When
        long result = basicBaseCreaterfidPrintService.getBasicBaseCreaterfidPrintDACCount(params, request);

        // Then
        assertEquals(expectedCount, result);
    }
}
