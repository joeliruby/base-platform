package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.*;
import com.matariky.commonservice.base.mapper.*;
import com.matariky.commonservice.base.vo.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDeviceTypeServiceTest {

    @InjectMocks
    private BasicBaseDeviceTypeService basicbasedevicetypeservice;

    @Mock
    private BasicBaseDeviceTypeMapper basicBaseDevicetypeMapper;
    @Mock
    private HttpServletRequest request;
    @Mock
    private CommonService commonService;
    @Mock
    private CommonDictService commonDictService;
    @Mock
    private CommonDictTypeService commonDictTypeService;
    @Mock
    private BasicBaseDeviceMapper basicBaseDeviceMapper;
    @Mock
    private BasicBaseDevicePackageMapper basicBaseDevicePackageMapper;
    @Mock
    private BasicBaseDeviceRuleMapper basicBaseDeviceRuleMapper;
    @Mock
    private BasicBaseDevicecommandMapper basicBaseDevicecommandMapper;
    @Mock
    private BasicBaseDevicecommandPackageMapper baseDevicecommandPackageMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDevicetypeAll() {
        // Given
        BasicBaseDeviceTypeListVO vo = new BasicBaseDeviceTypeListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("value");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(basicBaseDevicetypeMapper.getBasicBaseDevicetypeAll(any())).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceTypeInfoVO> result = basicbasedevicetypeservice.getBasicBaseDevicetypeAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDevicetypeWithOrg() {
        // Given
        BasicBaseDeviceTypeAddVO addVO = new BasicBaseDeviceTypeAddVO();
        String jwt = "jwt";
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractUserIdFromToken(jwt)).thenReturn("1");
        CommonDict deviceTypeCode = new CommonDict();
        deviceTypeCode.setDictName("DeviceType");
        when(commonService.getDeviceTypeCodeDict(anyString(), anyString())).thenReturn(deviceTypeCode);
        when(basicBaseDevicetypeMapper.selectOne(any())).thenReturn(null);
        when(basicBaseDevicetypeMapper.selectCount(any())).thenReturn(0L);

        // When
        basicbasedevicetypeservice.createBasicBaseDevicetypeWithOrg(addVO, jwt);

        // Then
        verify(basicBaseDevicetypeMapper, times(1)).createBasicBaseDevicetype(any());
    }

    @Test
    void testUpdateBasicBaseDevicetype() {
        // Given
        BasicBaseDeviceTypeUpdateVO updateVO = new BasicBaseDeviceTypeUpdateVO();
        String jwt = "jwt";
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractUserIdFromToken(jwt)).thenReturn("1");
        CommonDict deviceTypeCode = new CommonDict();
        deviceTypeCode.setDictName("DeviceType");
        when(commonService.getDeviceTypeCodeDict(anyString(), anyString())).thenReturn(deviceTypeCode);
        when(basicBaseDevicetypeMapper.selectCount(any())).thenReturn(0L);

        // When
        basicbasedevicetypeservice.updateBasicBaseDevicetype(updateVO, jwt);

        // Then
        verify(basicBaseDevicetypeMapper, times(1)).updateById(any());
    }

    @Test
    void testUpdateBasicBaseDevicetypeStatus() {
        // Given
        BasicBaseDevicetypeStatusVO vo = new BasicBaseDevicetypeStatusVO();
        vo.setId(1L);
        vo.setStatus(1);
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");

        // When
        basicbasedevicetypeservice.updateBasicBaseDevicetypeStatus(vo);

        // Then
        verify(basicBaseDevicetypeMapper, times(1)).updateById(any());
    }

    @Test
    void testDelBasicBaseDevicetypeById() {
        // Given
        Long id = 1L;
        when(basicBaseDeviceMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDevicePackageMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDeviceRuleMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDevicetypeMapper.delBasicBaseDevicetypeById(id)).thenReturn(1);

        // When
        int result = basicbasedevicetypeservice.delBasicBaseDevicetypeById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetOptionList() {
        // Given
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(request.getHeader("id")).thenReturn("12345");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("value");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(basicBaseDevicetypeMapper.getOptionList(any())).thenReturn(Collections.emptyList());

        // When
        List<DeviceTypeOption> result = basicbasedevicetypeservice.getOptionList();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseDevicetypeById() {
        // Given
        Long id = 1L;
        BasicBaseDeviceType typeInfo = new BasicBaseDeviceType();
        when(basicBaseDevicetypeMapper.getBasicBaseDevicetypeById(id)).thenReturn(typeInfo);
        when(basicBaseDevicecommandMapper.selectList(any())).thenReturn(Collections.emptyList());

        // When
        BasicBaseDeviceTypeInfo result = basicbasedevicetypeservice.getBasicBaseDevicetypeById(id);

        // Then
        assertNotNull(result);
    }
}
