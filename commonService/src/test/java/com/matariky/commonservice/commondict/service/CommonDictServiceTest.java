package com.matariky.commonservice.commondict.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import org.mockito.Mock;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;

@SpringBootTest
public class CommonDictServiceTest {

    @InjectMocks
    private CommonDictService commondictservice;

    @Mock
    private CommonDictMapper commonDictMapper;

    @Mock
    private CommonDictTypeMapper commonDictTypeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonDictAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        List<CommonDict> expectedList = new ArrayList<>();
        when(commonDictMapper.getCommonDictAll(params)).thenReturn(expectedList);

        // When
        List<CommonDict> result = commondictservice.getCommonDictAll(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateCommonDict() {
        // Given
        CommonDict commonDict = new CommonDict();
        when(commonDictMapper.createCommonDict(commonDict)).thenReturn(1);

        // When
        int result = commondictservice.createCommonDict(commonDict);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonDict() {
        // Given
        CommonDict commonDict = new CommonDict();
        when(commonDictMapper.updateCommonDict(commonDict)).thenReturn(1);

        // When
        int result = commondictservice.updateCommonDict(commonDict);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonDictById() {
        // Given
        Long id = 1L;
        when(commonDictMapper.delCommonDictById(id)).thenReturn(1);

        // When
        int result = commondictservice.delCommonDictById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonDictById() {
        // Given
        Long id = 1L;
        CommonDict expectedDict = new CommonDict();
        when(commonDictMapper.getCommonDictById(id)).thenReturn(expectedDict);

        // When
        CommonDict result = commondictservice.getCommonDictById(id);

        // Then
        assertEquals(expectedDict, result);
    }

    @Test
    void testUpdateValueByKeyAndTenantId() {
        // Given
        String dictTypeKey = "typeKey";
        String dictKey = "key";
        String dictValue = "value";
        String tenantId = "tenantId";
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        List<CommonDictType> dictTypes = Collections.singletonList(commonDictType);
        when(commonDictTypeMapper.selectByMap(anyMap())).thenReturn(dictTypes);
        CommonDict commonDict = new CommonDict();
        when(commonDictMapper.getDictionaryItemById(anyString(), anyString(), anyString())).thenReturn(commonDict);

        // When
        commondictservice.updateValueByKeyAndTenantId(dictTypeKey, dictKey, dictValue, tenantId);

        // Then
        verify(commonDictMapper).updateCommonDict(commonDict);
    }

    // Add more test methods for other methods in CommonDictService
}
