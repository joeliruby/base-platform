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
import com.matariky.commonservice.base.bean.BasicBaseDevicecommand;
import com.matariky.commonservice.base.mapper.BasicBaseDevicecommandMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseDevicecommandServiceTest {

    @InjectMocks
    private BasicBaseDevicecommandService basicbasedevicecommandservice;

    @Mock
    private BasicBaseDevicecommandMapper basicBaseDevicecommandMapper;

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
    void testGetBasicBaseDevicecommandAll() {
        // Given
        BasicBaseDevicecommand vo = new BasicBaseDevicecommand();
        String hid = "12345";
        String resourceIdDictKey = "dp1234";
        String tenantId = "tenantId";
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        CommonDict dict = new CommonDict();
        dict.setDictValue("someValue");

        when(request.getHeader("id")).thenReturn(hid);
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn(tenantId);
        when(commonDictTypeService.getDictTypeByKey(tenantId, PermissionConstant.DATA_ACCESS_PERMISSION))
                .thenReturn(commonDictType);
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
                commonDictType.getId())).thenReturn(dict);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBaseDevicecommandMapper.getBasicBaseDevicecommandAll(vo)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDevicecommand> result = basicbasedevicecommandservice.getBasicBaseDevicecommandAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDevicecommand() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicBaseDevicecommandMapper.createBasicBaseDevicecommand(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandservice.createBasicBaseDevicecommand(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDevicecommand() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicBaseDevicecommandMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandservice.updateBasicBaseDevicecommand(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDevicecommandById() {
        // Given
        int id = 1;
        when(basicBaseDevicecommandMapper.delBasicBaseDevicecommandById(id)).thenReturn(1);

        // When
        int result = basicbasedevicecommandservice.delBasicBaseDevicecommandById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDevicecommandById() {
        // Given
        int id = 1;
        BasicBaseDevicecommand command = new BasicBaseDevicecommand();
        when(basicBaseDevicecommandMapper.getBasicBaseDevicecommandById(id)).thenReturn(command);

        // When
        BasicBaseDevicecommand result = basicbasedevicecommandservice.getBasicBaseDevicecommandById(id);

        // Then
        assertNotNull(result);
        assertEquals(command, result);
    }

    @Test
    void testGetBasicBaseDevicecommandDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseDevicecommandMapper.getBasicBaseDevicecommandDAC(params)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDevicecommand> result = basicbasedevicecommandservice.getBasicBaseDevicecommandDAC(params,
                request);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseDevicecommandDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseDevicecommandMapper.getBasicBaseDevicecommandDACCount(params)).thenReturn(1L);

        // When
        Long result = basicbasedevicecommandservice.getBasicBaseDevicecommandDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
