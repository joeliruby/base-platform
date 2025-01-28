package com.matariky.bizservice.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintParameter;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintParameterMapper;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseRfidprintParameterServiceTest {

    @InjectMocks
    private BasicBaseRfidprintParameterService basicBaseRfidprintParameterService;

    @Mock
    private BasicBaseRfidprintParameterMapper basicBaseRfidprintParameterMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidprintParameterAll() {
        // Given
        int pageIndex = 1;
        int perPage = 10;
        List<BasicBaseRfidprintParameter> expectedList = List.of(new BasicBaseRfidprintParameter());
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAll()).thenReturn(expectedList);

        // When
        List<BasicBaseRfidprintParameter> result = basicBaseRfidprintParameterService
                .getBasicBaseRfidprintParameterAll(pageIndex, perPage);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidprintParameterAllCount() {
        // Given
        int expectedCount = 5;
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBaseRfidprintParameterService.getBasicBaseRfidprintParameterAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseRfidprintParameter() {
        // Given
        BasicBaseRfidprintParameter bean = new BasicBaseRfidprintParameter();
        when(basicBaseRfidprintParameterMapper.createBasicBaseRfidprintParameter(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterService.createBasicBaseRfidprintParameter(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprintParameter() {
        // Given
        BasicBaseRfidprintParameter bean = new BasicBaseRfidprintParameter();
        when(basicBaseRfidprintParameterMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterService.updateBasicBaseRfidprintParameter(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidprintParameterById() {
        // Given
        int id = 1;
        when(basicBaseRfidprintParameterMapper.delBasicBaseRfidprintParameterById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterService.delBasicBaseRfidprintParameterById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintParameterById() {
        // Given
        int id = 1;
        BasicBaseRfidprintParameter expectedBean = new BasicBaseRfidprintParameter();
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterById(id)).thenReturn(expectedBean);

        // When
        BasicBaseRfidprintParameter result = basicBaseRfidprintParameterService.getBasicBaseRfidprintParameterById(id);

        // Then
        assertEquals(expectedBean, result);
    }

    @Test
    void testCreateBasicBaseRfidprintParameterWithOrg() {
        // Given
        BasicBaseRfidprintParameter bean = new BasicBaseRfidprintParameter();
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBaseRfidprintParameterMapper.createBasicBaseRfidprintParameter(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterService.createBasicBaseRfidprintParameterWithOrg(bean, request);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintParameterDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        List<BasicBaseRfidprintParameter> expectedList = List.of(new BasicBaseRfidprintParameter());
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidprintParameter> result = basicBaseRfidprintParameterService
                .getBasicBaseRfidprintParameterDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidprintParameterDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        long expectedCount = 5L;
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterDACCount(params))
                .thenReturn(expectedCount);

        // When
        long result = basicBaseRfidprintParameterService.getBasicBaseRfidprintParameterDACCount(params, request);

        // Then
        assertEquals(expectedCount, result);
    }
}
