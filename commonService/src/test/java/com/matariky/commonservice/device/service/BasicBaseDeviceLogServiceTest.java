package com.matariky.commonservice.device.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.device.bean.BasicBaseDeviceLog;
import com.matariky.commonservice.device.mapper.BasicBaseDeviceLogMapper;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;

@SpringBootTest
public class BasicBaseDeviceLogServiceTest {

    @InjectMocks
    private BasicBaseDeviceLogService basicBaseDeviceLogService;

    @Mock
    private BasicBaseDeviceLogMapper basicBaseDeviceLogMapper;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private CommonDictService commonDictService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceLogAll() {
        // Given
        BasicBaseDeviceLog vo = new BasicBaseDeviceLog();
        vo.setBusinessTimeEnd("1234567890");
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(12L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("dictValue");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(basicBaseDeviceLogMapper.getBasicBaseDeviceLogAll(any())).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceLog> result = basicBaseDeviceLogService.getBasicBaseDeviceLogAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testExport() {
        // Given
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();
        when(basicBaseDeviceLogMapper.getBasicBaseDeviceLogAll(any())).thenReturn(Collections.emptyList());

        // When
        basicBaseDeviceLogService.export(bean);

        // Then
        // Verify interactions and assert expected results
    }

    @Test
    void testGetBasicBaseDeviceLogAllCount() {
        // Given
        when(basicBaseDeviceLogMapper.getBasicBaseDeviceLogAllCount()).thenReturn(10);

        // When
        int result = basicBaseDeviceLogService.getBasicBaseDeviceLogAllCount();

        // Then
        assertEquals(10, result);
    }

    @Test
    void testCreateBasicBaseDeviceLog() {
        // Given
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();
        when(basicBaseDeviceLogMapper.createBasicBaseDeviceLog(any())).thenReturn(1);

        // When
        int result = basicBaseDeviceLogService.createBasicBaseDeviceLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDeviceLog() {
        // Given
        BasicBaseDeviceLog bean = new BasicBaseDeviceLog();
        when(basicBaseDeviceLogMapper.updateById(any())).thenReturn(1);

        // When
        int result = basicBaseDeviceLogService.updateBasicBaseDeviceLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceLogById() {
        // Given
        when(basicBaseDeviceLogMapper.delBasicBaseDeviceLogById(anyInt())).thenReturn(1);

        // When
        int result = basicBaseDeviceLogService.delBasicBaseDeviceLogById(1);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDeviceLogById() {
        // Given
        BasicBaseDeviceLog log = new BasicBaseDeviceLog();
        when(basicBaseDeviceLogMapper.getBasicBaseDeviceLogById(anyInt())).thenReturn(log);

        // When
        BasicBaseDeviceLog result = basicBaseDeviceLogService.getBasicBaseDeviceLogById(1);

        // Then
        assertNotNull(result);
    }
}
