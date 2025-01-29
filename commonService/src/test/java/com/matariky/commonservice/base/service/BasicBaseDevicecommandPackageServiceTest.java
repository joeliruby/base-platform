package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.matariky.commonservice.base.bean.BasicBaseDevicecommandPackage;
import com.matariky.commonservice.base.mapper.BasicBaseDevicecommandPackageMapper;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseDevicecommandPackageServiceTest {

    @InjectMocks
    private BasicBaseDevicecommandPackageService basicbasedevicecommandpackageservice;

    @Mock
    private BasicBaseDevicecommandPackageMapper basicBaseDevicecommandPackageMapper;

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
    void testGetBasicBaseDevicecommandPackageAll() {
        // Given
        BasicBaseDevicecommandPackage vo = new BasicBaseDevicecommandPackage();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(null);
        when(basicBaseDevicecommandPackageMapper.getBasicBaseDevicecommandPackageAll(vo)).thenReturn(List.of(vo));

        // When
        List<BasicBaseDevicecommandPackage> result = basicbasedevicecommandpackageservice
                .getBasicBaseDevicecommandPackageAll(vo);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testCreateBasicBaseDevicecommandPackage() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicBaseDevicecommandPackageMapper.createBasicBaseDevicecommandPackage(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandpackageservice.createBasicBaseDevicecommandPackage(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDevicecommandPackage() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicBaseDevicecommandPackageMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandpackageservice.updateBasicBaseDevicecommandPackage(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDevicecommandPackageById() {
        // Given
        int id = 1;
        when(basicBaseDevicecommandPackageMapper.delBasicBaseDevicecommandPackageById(id)).thenReturn(1);

        // When
        int result = basicbasedevicecommandpackageservice.delBasicBaseDevicecommandPackageById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDevicecommandPackageById() {
        // Given
        int id = 1;
        BasicBaseDevicecommandPackage expected = new BasicBaseDevicecommandPackage();
        when(basicBaseDevicecommandPackageMapper.getBasicBaseDevicecommandPackageById(id)).thenReturn(expected);

        // When
        BasicBaseDevicecommandPackage result = basicbasedevicecommandpackageservice
                .getBasicBaseDevicecommandPackageById(id);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseDevicecommandPackageDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseDevicecommandPackageMapper.getBasicBaseDevicecommandPackageDAC(params))
                .thenReturn(List.of(new BasicBaseDevicecommandPackage()));

        // When
        List<BasicBaseDevicecommandPackage> result = basicbasedevicecommandpackageservice
                .getBasicBaseDevicecommandPackageDAC(params, request);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetBasicBaseDevicecommandPackageDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseDevicecommandPackageMapper.getBasicBaseDevicecommandPackageDACCount(params)).thenReturn(1L);

        // When
        Long result = basicbasedevicecommandpackageservice.getBasicBaseDevicecommandPackageDACCount(params, request);

        // Then
        assertNotNull(result);
        assertEquals(1L, result);
    }
}
