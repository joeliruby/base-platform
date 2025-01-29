package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.mapper.BasicBaseFormConfigMapper;
import com.matariky.commonservice.base.mapper.BasicBaseFormExtendMapper;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.exception.QslException;

@SpringBootTest
public class CommonServiceTest {

    @InjectMocks
    private CommonService commonService;

    @Mock
    private MinioUtil minioUtil;
    @Mock
    private CommonDictTypeMapper commonDictTypeMapper;
    @Mock
    private CommonDictMapper commonDictMapper;
    @Mock
    private BasicBaseFormConfigMapper basicBaseFormConfigMapper;
    @Mock
    private BasicBaseFormExtendMapper basicBaseFormExtendMapper;
    @Mock
    private BasicBaseGoodsMapper basicBaseGoodsMapper;
    @Mock
    private CommonDictTypeService commonDictTypeService;
    @Mock
    private CommonDictService commonDictService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateQRCodeImage() {
        // Given
        String text = "sampleText";
        String tenantId = "tenant1";
        String bucket = "bucket1";
        String expectedUrl = "api/v1/tenant/tenant1/file/downloadFile?bucket=bucket1&objectName=someFileName.png";

        // When
        String result = commonService.generateQRCodeImage(text, tenantId, bucket);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("api/v1/tenant/tenant1/file/downloadFile"));
    }

    @Test
    void testGetDeviceTypeCodeDict() {
        // Given
        String dictKey = "someKey";
        String tenantId = "tenant1";
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);
        CommonDict expectedDict = new CommonDict();
        when(commonDictMapper.selectOne(any())).thenReturn(expectedDict);

        // When
        CommonDict result = commonService.getDeviceTypeCodeDict(dictKey, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(expectedDict, result);
    }

    @Test
    void testGetDeviceTypeCodeDict_ThrowsException() {
        // Given
        String dictKey = "someKey";
        String tenantId = "tenant1";
        when(commonDictTypeMapper.selectOne(any())).thenReturn(null);

        // When & Then
        assertThrows(QslException.class, () -> commonService.getDeviceTypeCodeDict(dictKey, tenantId));
    }

    @Test
    void testGetAPPTypeCodeDict() {
        // Given
        String dictValue = "someValue";
        String tenantId = "tenant1";
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);
        CommonDict expectedDict = new CommonDict();
        when(commonDictMapper.selectOne(any())).thenReturn(expectedDict);

        // When
        CommonDict result = commonService.getAPPTypeCodeDict(dictValue, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(expectedDict, result);
    }

    @Test
    void testGetAPPTypeCodeDict_ThrowsException() {
        // Given
        String dictValue = "someValue";
        String tenantId = "tenant1";
        when(commonDictTypeMapper.selectOne(any())).thenReturn(null);

        // When & Then
        assertThrows(QslException.class, () -> commonService.getAPPTypeCodeDict(dictValue, tenantId));
    }

    @Test
    void testGetDeviceDbmDict() {
        // Given
        String tenantId = "tenant1";
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);
        List<CommonDict> expectedDicts = new ArrayList<>();
        when(commonDictMapper.selectList(any())).thenReturn(expectedDicts);

        // When
        List<CommonDict> result = commonService.getDeviceDbmDict(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(expectedDicts, result);
    }

    @Test
    void testGetDeviceDbmDict_ReturnsEmptyList() {
        // Given
        String tenantId = "tenant1";
        when(commonDictTypeMapper.selectOne(any())).thenReturn(null);

        // When
        List<CommonDict> result = commonService.getDeviceDbmDict(tenantId);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // Add more test methods for other methods in CommonService
}
