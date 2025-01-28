package com.matariky.bizservice.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplate;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidtemplateMapper;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseRfidtemplateServiceTest {

    @InjectMocks
    private BasicBaseRfidtemplateService basicBaseRfidtemplateService;

    @Mock
    private BasicBaseRfidtemplateMapper basicBaseRfidtemplateMapper;

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
    void testGetBasicBaseRfidtemplateAll() {
        // Given
        BasicBaseRfidtemplate vo = new BasicBaseRfidtemplate();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(),
                org.mockito.ArgumentMatchers.anyLong()))
                .thenReturn(null);
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");

        // When
        List<BasicBaseRfidtemplate> result = basicBaseRfidtemplateService.getBasicBaseRfidtemplateAll(vo);

        // Then
        assertNotNull(result);
        verify(basicBaseRfidtemplateMapper).getBasicBaseRfidtemplateAll(vo);
    }

    @Test
    void testCreateBasicBaseRfidtemplate() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateMapper.createBasicBaseRfidtemplate(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateService.createBasicBaseRfidtemplate(bean);

        // Then
        assertEquals(1, result);
        verify(basicBaseRfidtemplateMapper).createBasicBaseRfidtemplate(bean);
    }

    @Test
    void testUpdateBasicBaseRfidtemplate() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateService.updateBasicBaseRfidtemplate(bean);

        // Then
        assertEquals(1, result);
        verify(basicBaseRfidtemplateMapper).updateById(bean);
    }

    @Test
    void testDelBasicBaseRfidtemplateById() {
        // Given
        int id = 1;
        when(basicBaseRfidtemplateMapper.delBasicBaseRfidtemplateById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateService.delBasicBaseRfidtemplateById(id);

        // Then
        assertEquals(1, result);
        verify(basicBaseRfidtemplateMapper).delBasicBaseRfidtemplateById(id);
    }

    @Test
    void testGetBasicBaseRfidtemplateById() {
        // Given
        int id = 1;
        BasicBaseRfidtemplate expected = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateById(id)).thenReturn(expected);

        // When
        BasicBaseRfidtemplate result = basicBaseRfidtemplateService.getBasicBaseRfidtemplateById(id);

        // Then
        assertEquals(expected, result);
        verify(basicBaseRfidtemplateMapper).getBasicBaseRfidtemplateById(id);
    }

    @Test
    void testGetBasicBaseRfidtemplateDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDAC(params))
                .thenReturn(List.of(new BasicBaseRfidtemplate()));

        // When
        List<BasicBaseRfidtemplate> result = basicBaseRfidtemplateService.getBasicBaseRfidtemplateDAC(params, request);

        // Then
        assertNotNull(result);
        verify(basicBaseRfidtemplateMapper).getBasicBaseRfidtemplateDAC(params);
    }

    @Test
    void testGetBasicBaseRfidtemplateDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDACCount(params)).thenReturn(10L);

        // When
        Long result = basicBaseRfidtemplateService.getBasicBaseRfidtemplateDACCount(params, request);

        // Then
        assertEquals(10L, result);
        verify(basicBaseRfidtemplateMapper).getBasicBaseRfidtemplateDACCount(params);
    }
}
