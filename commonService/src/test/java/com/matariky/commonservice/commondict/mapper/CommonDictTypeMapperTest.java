package com.matariky.commonservice.commondict.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.commonservice.commondict.bean.CommonDictType;

@SpringBootTest
public class CommonDictTypeMapperTest {

    @InjectMocks
    private CommonDictTypeMapper commondicttypemapper;

    @Mock
    private CommonDictTypeMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonDictTypeAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        Page<CommonDictType> expectedPage = new Page<>();
        when(mockMapper.getCommonDictTypeAll(params)).thenReturn(expectedPage);

        // When
        Page<CommonDictType> result = commondicttypemapper.getCommonDictTypeAll(params);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetCommonDictTypeAllCount() {
        // Given
        Map<String, Object> params = new HashMap<>();
        int expectedCount = 10;
        when(mockMapper.getCommonDictTypeAllCount(params)).thenReturn(expectedCount);

        // When
        int result = commondicttypemapper.getCommonDictTypeAllCount(params);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateCommonDictType() {
        // Given
        CommonDictType bean = new CommonDictType();
        int expectedResult = 1;
        when(mockMapper.createCommonDictType(bean)).thenReturn(expectedResult);

        // When
        int result = commondicttypemapper.createCommonDictType(bean);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    void testUpdateCommonDictType() {
        // Given
        CommonDictType bean = new CommonDictType();
        int expectedResult = 1;
        when(mockMapper.updateCommonDictType(bean)).thenReturn(expectedResult);

        // When
        int result = commondicttypemapper.updateCommonDictType(bean);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    void testDelCommonDictTypeById() {
        // Given
        Long id = 1L;
        int expectedResult = 1;
        when(mockMapper.delCommonDictTypeById(id)).thenReturn(expectedResult);

        // When
        int result = commondicttypemapper.delCommonDictTypeById(id);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetCommonDictTypeById() {
        // Given
        String id = "1";
        CommonDictType expectedDictType = new CommonDictType();
        when(mockMapper.getCommonDictTypeById(id)).thenReturn(expectedDictType);

        // When
        CommonDictType result = commondicttypemapper.getCommonDictTypeById(id);

        // Then
        assertEquals(expectedDictType, result);
    }

    // Add more test methods for other methods in CommonDictTypeMapper
}
