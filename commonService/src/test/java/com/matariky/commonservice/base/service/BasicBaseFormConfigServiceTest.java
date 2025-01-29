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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.matariky.commonservice.base.bean.BasicBaseFormConfig;
import com.matariky.commonservice.base.mapper.BasicBaseFormConfigMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseFormConfigServiceTest {

    @InjectMocks
    private BasicBaseFormConfigService basicBaseFormConfigService;

    @Mock
    private BasicBaseFormConfigMapper basicBaseFormConfigMapper;

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
    void testGetBasicBaseFormConfigAll() {
        // Given
        BasicBaseFormConfig vo = new BasicBaseFormConfig();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("strategyCode");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        List<BasicBaseFormConfig> expectedList = Collections.singletonList(new BasicBaseFormConfig());
        when(basicBaseFormConfigMapper.getBasicBaseFormConfigAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseFormConfig> result = basicBaseFormConfigService.getBasicBaseFormConfigAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseFormConfig() {
        // Given
        BasicBaseFormConfig bean = new BasicBaseFormConfig();
        when(basicBaseFormConfigMapper.createBasicBaseFormConfig(bean)).thenReturn(1);

        // When
        int result = basicBaseFormConfigService.createBasicBaseFormConfig(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseFormConfig() {
        // Given
        BasicBaseFormConfig bean = new BasicBaseFormConfig();
        when(basicBaseFormConfigMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseFormConfigService.updateBasicBaseFormConfig(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseFormConfigById() {
        // Given
        Long id = 1L;
        when(basicBaseFormConfigMapper.delBasicBaseFormConfigById(id)).thenReturn(1);

        // When
        int result = basicBaseFormConfigService.delBasicBaseFormConfigById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseFormConfigById() {
        // Given
        Long id = 1L;
        BasicBaseFormConfig expectedConfig = new BasicBaseFormConfig();
        when(basicBaseFormConfigMapper.getBasicBaseFormConfigById(id)).thenReturn(expectedConfig);

        // When
        BasicBaseFormConfig result = basicBaseFormConfigService.getBasicBaseFormConfigById(id);

        // Then
        assertEquals(expectedConfig, result);
    }

    @Test
    void testGetBasicBaseFormConfigDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseFormConfig> expectedList = Collections.singletonList(new BasicBaseFormConfig());
        when(basicBaseFormConfigMapper.getBasicBaseFormConfigDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseFormConfig> result = basicBaseFormConfigService.getBasicBaseFormConfigDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseFormConfigDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseFormConfigMapper.getBasicBaseFormConfigDACCount(params)).thenReturn(1L);

        // When
        Long result = basicBaseFormConfigService.getBasicBaseFormConfigDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
