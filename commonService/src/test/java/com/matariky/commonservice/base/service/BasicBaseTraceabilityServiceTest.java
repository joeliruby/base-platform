package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import com.matariky.commonservice.base.mapper.BasicBaseTraceabilityMapper;
import com.matariky.commonservice.base.bean.BasicBaseTraceability;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BasicBaseTraceabilityServiceTest {

    @InjectMocks
    private BasicBaseTraceabilityService basicBaseTraceabilityService;

    @Mock
    private BasicBaseTraceabilityMapper basicBaseTraceabilityMapper;

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
    void testGetBasicBaseTraceabilityAll() {
        // Given
        BasicBaseTraceability vo = new BasicBaseTraceability();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        // Add more mock setups as needed

        // When
        List<BasicBaseTraceability> result = basicBaseTraceabilityService.getBasicBaseTraceabilityAll(vo);

        // Then
        assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
    void testGetBasicBaseTraceabilityAllCount() {
        // Given
        when(basicBaseTraceabilityMapper.getBasicBaseTraceabilityAllCount()).thenReturn(10);

        // When
        int result = basicBaseTraceabilityService.getBasicBaseTraceabilityAllCount();

        // Then
        assertEquals(10, result);
    }

    @Test
    void testCreateBasicBaseTraceability() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityMapper.createBasicBaseTraceability(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityService.createBasicBaseTraceability(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseTraceability() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityService.updateBasicBaseTraceability(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseTraceabilityById() {
        // Given
        int id = 1;
        when(basicBaseTraceabilityMapper.delBasicBaseTraceabilityById(id)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityService.delBasicBaseTraceabilityById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseTraceabilityById() {
        // Given
        int id = 1;
        BasicBaseTraceability expected = new BasicBaseTraceability();
        when(basicBaseTraceabilityMapper.getBasicBaseTraceabilityById(id)).thenReturn(expected);

        // When
        BasicBaseTraceability result = basicBaseTraceabilityService.getBasicBaseTraceabilityById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseTraceabilityDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseTraceabilityMapper.getBasicBaseTraceabilityDAC(params))
                .thenReturn(List.of(new BasicBaseTraceability()));

        // When
        List<BasicBaseTraceability> result = basicBaseTraceabilityService.getBasicBaseTraceabilityDAC(params, request);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetBasicBaseTraceabilityDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseTraceabilityMapper.getBasicBaseTraceabilityDACCount(params)).thenReturn(10L);

        // When
        Long result = basicBaseTraceabilityService.getBasicBaseTraceabilityDACCount(params, request);

        // Then
        assertEquals(10L, result);
    }
}
