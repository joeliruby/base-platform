package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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

import com.matariky.commonservice.base.bean.BasicBaseFormExtend;
import com.matariky.commonservice.base.mapper.BasicBaseFormExtendMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseFormExtendServiceTest {

    @InjectMocks
    private BasicBaseFormExtendService basicBaseFormExtendService;

    @Mock
    private BasicBaseFormExtendMapper basicBaseFormExtendMapper;

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
    void testGetBasicBaseFormExtendAll() {
        // Given
        BasicBaseFormExtend vo = new BasicBaseFormExtend();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("strategyCode");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(basicBaseFormExtendMapper.getBasicBaseFormExtendAll(vo)).thenReturn(Collections.singletonList(vo));

        // When
        List<BasicBaseFormExtend> result = basicBaseFormExtendService.getBasicBaseFormExtendAll(vo);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("strategyCode", vo.getStrategyCode());
    }

    @Test
    void testCreateBasicBaseFormExtend() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicBaseFormExtendMapper.createBasicBaseFormExtend(bean)).thenReturn(1);

        // When
        int result = basicBaseFormExtendService.createBasicBaseFormExtend(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseFormExtend() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicBaseFormExtendMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseFormExtendService.updateBasicBaseFormExtend(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseFormExtendById() {
        // Given
        Long id = 1L;
        when(basicBaseFormExtendMapper.delBasicBaseFormExtendById(id)).thenReturn(1);

        // When
        int result = basicBaseFormExtendService.delBasicBaseFormExtendById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseFormExtendById() {
        // Given
        Long id = 1L;
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicBaseFormExtendMapper.getBasicBaseFormExtendById(id)).thenReturn(bean);

        // When
        BasicBaseFormExtend result = basicBaseFormExtendService.getBasicBaseFormExtendById(id);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetBasicBaseFormExtendDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseFormExtendMapper.getBasicBaseFormExtendDAC(params))
                .thenReturn(Collections.singletonList(new BasicBaseFormExtend()));

        // When
        List<BasicBaseFormExtend> result = basicBaseFormExtendService.getBasicBaseFormExtendDAC(params, request);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetBasicBaseFormExtendDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseFormExtendMapper.getBasicBaseFormExtendDACCount(params)).thenReturn(1L);

        // When
        Long result = basicBaseFormExtendService.getBasicBaseFormExtendDACCount(params, request);

        // Then
        assertNotNull(result);
        assertEquals(1L, result);
    }
}
