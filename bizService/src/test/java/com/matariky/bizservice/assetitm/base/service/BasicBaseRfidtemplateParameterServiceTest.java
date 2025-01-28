package com.matariky.bizservice.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplateParameter;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidtemplateParameterMapper;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseRfidtemplateParameterServiceTest {

    @InjectMocks
    private BasicBaseRfidtemplateParameterService basicBaseRfidtemplateParameterService;

    @Mock
    private BasicBaseRfidtemplateParameterMapper basicBaseRfidtemplateParameterMapper;

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
    void testGetBasicBaseRfidtemplateParameterAll() {
        // Given
        BasicBaseRfidtemplateParameter vo = new BasicBaseRfidtemplateParameter();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(null);
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterAll(vo))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidtemplateParameter> result = basicBaseRfidtemplateParameterService
                .getBasicBaseRfidtemplateParameterAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseRfidtemplateParameter() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.createBasicBaseRfidtemplateParameter(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateParameterService.createBasicBaseRfidtemplateParameter(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidtemplateParameter() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateParameterService.updateBasicBaseRfidtemplateParameter(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidtemplateParameterById() {
        // Given
        int id = 1;
        when(basicBaseRfidtemplateParameterMapper.delBasicBaseRfidtemplateParameterById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateParameterService.delBasicBaseRfidtemplateParameterById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterById() {
        // Given
        int id = 1;
        BasicBaseRfidtemplateParameter expected = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterById(id)).thenReturn(expected);

        // When
        BasicBaseRfidtemplateParameter result = basicBaseRfidtemplateParameterService
                .getBasicBaseRfidtemplateParameterById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterDAC(params))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidtemplateParameter> result = basicBaseRfidtemplateParameterService
                .getBasicBaseRfidtemplateParameterDAC(params, request);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterDACCount(params)).thenReturn(0L);

        // When
        Long result = basicBaseRfidtemplateParameterService.getBasicBaseRfidtemplateParameterDACCount(params, request);

        // Then
        assertEquals(0L, result);
    }
}
