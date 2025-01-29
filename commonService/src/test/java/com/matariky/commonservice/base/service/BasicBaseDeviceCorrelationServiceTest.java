package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseDeviceCorrelation;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceCorrelationMapper;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseDeviceCorrelationServiceTest {

    @InjectMocks
    private BasicBaseDeviceCorrelationService basicBaseDeviceCorrelationService;

    @Mock
    private BasicBaseDeviceCorrelationMapper basicBaseDeviceCorrelationMapper;

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
    void testGetBasicBaseDeviceCorrelationAll() {
        // Given
        BasicBaseDeviceCorrelation vo = new BasicBaseDeviceCorrelation();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(null);
        when(basicBaseDeviceCorrelationMapper.getBasicBaseDeviceCorrelationAll(vo)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceCorrelation> result = basicBaseDeviceCorrelationService
                .getBasicBaseDeviceCorrelationAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDeviceCorrelation() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicBaseDeviceCorrelationMapper.createBasicBaseDeviceCorrelation(bean)).thenReturn(1);

        // When
        int result = basicBaseDeviceCorrelationService.createBasicBaseDeviceCorrelation(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDeviceCorrelation() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicBaseDeviceCorrelationMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseDeviceCorrelationService.updateBasicBaseDeviceCorrelation(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceCorrelationById() {
        // Given
        int id = 1;
        when(basicBaseDeviceCorrelationMapper.delBasicBaseDeviceCorrelationById(id)).thenReturn(1);

        // When
        int result = basicBaseDeviceCorrelationService.delBasicBaseDeviceCorrelationById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDeviceCorrelationById() {
        // Given
        int id = 1;
        BasicBaseDeviceCorrelation expected = new BasicBaseDeviceCorrelation();
        when(basicBaseDeviceCorrelationMapper.getBasicBaseDeviceCorrelationById(id)).thenReturn(expected);

        // When
        BasicBaseDeviceCorrelation result = basicBaseDeviceCorrelationService.getBasicBaseDeviceCorrelationById(id);

        // Then
        assertEquals(expected, result);
    }

    // Add more test methods for other methods in BasicBaseDeviceCorrelationService
}
