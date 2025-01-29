package com.matariky.commonservice.commondict.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;

@SpringBootTest
public class CommonDictTypeServiceTest {

    @InjectMocks
    private CommonDictTypeService commondicttypeservice;

    @Mock
    private CommonDictTypeMapper commonDictTypeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonDictTypeAll() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Page<CommonDictType> expectedPage = new Page<>();
        expectedPage.addAll(Collections.emptyList());
        when(commonDictTypeMapper.getCommonDictTypeAll(params)).thenReturn(expectedPage);

        // When
        Page<CommonDictType> result = commondicttypeservice.getCommonDictTypeAll(params);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetCommonDictTypeAllCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        int expectedCount = 10;
        when(commonDictTypeMapper.getCommonDictTypeAllCount(params)).thenReturn(expectedCount);

        // When
        int result = commondicttypeservice.getCommonDictTypeAllCount(params);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateCommonDictType() {
        // Given
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeMapper.createCommonDictType(commonDictType)).thenReturn(1);

        // When
        int result = commondicttypeservice.createCommonDictType(commonDictType);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonDictType() {
        // Given
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeMapper.updateCommonDictType(commonDictType)).thenReturn(1);

        // When
        int result = commondicttypeservice.updateCommonDictType(commonDictType);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonDictTypeById() {
        // Given
        Long id = 1L;
        when(commonDictTypeMapper.delCommonDictTypeById(id)).thenReturn(1);

        // When
        int result = commondicttypeservice.delCommonDictTypeById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonDictTypeById() {
        // Given
        String id = "1";
        CommonDictType expectedCommonDictType = new CommonDictType();
        when(commonDictTypeMapper.getCommonDictTypeById(id)).thenReturn(expectedCommonDictType);

        // When
        CommonDictType result = commondicttypeservice.getCommonDictTypeById(id);

        // Then
        assertEquals(expectedCommonDictType, result);
    }

    @Test
    void testGetDictsByDictTypeKey() {
        // Given
        String tenantId = "tenant1";
        String dictTypeKey = "key1";
        List<CommonDict> expectedDicts = Collections.emptyList();
        when(commonDictTypeMapper.getDictsByDictTypeKey(tenantId, dictTypeKey, 1L, 0L)).thenReturn(expectedDicts);

        // When
        List<CommonDict> result = commondicttypeservice.getDictsByDictTypeKey(tenantId, dictTypeKey);

        // Then
        assertEquals(expectedDicts, result);
    }

    @Test
    void testUpdateDeleteTimeById() {
        // Given
        String[] ids = { "1", "2" };
        when(commonDictTypeMapper.updateDeleteTimeById(ids)).thenReturn(2);

        // When
        int result = commondicttypeservice.updateDeleteTimeById(ids);

        // Then
        assertEquals(2, result);
    }

    @Test
    void testGetDictTypeByKey() {
        // Given
        String tenantId = "tenant1";
        String dictTypeKey = "key1";
        CommonDictType expectedCommonDictType = new CommonDictType();
        when(commonDictTypeMapper.getDictTypeByKey(tenantId, dictTypeKey)).thenReturn(expectedCommonDictType);

        // When
        CommonDictType result = commondicttypeservice.getDictTypeByKey(tenantId, dictTypeKey);

        // Then
        assertEquals(expectedCommonDictType, result);
    }
}
