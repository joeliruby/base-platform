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
import com.matariky.commonservice.commondict.bean.CommonDict;

@SpringBootTest
public class CommonDictMapperTest {

    @InjectMocks
    private CommonDictMapper commondictmapper;

    @Mock
    private CommonDictMapper mockCommonDictMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonDictAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");

        // When
        when(mockCommonDictMapper.getCommonDictAll(params)).thenReturn(null);

        // Then
        assertNull(commondictmapper.getCommonDictAll(params));
    }

    @Test
    void testGetCommonDictById() {
        // Given
        Long id = 1L;
        CommonDict commonDict = new CommonDict();
        commonDict.setId(id);

        // When
        when(mockCommonDictMapper.getCommonDictById(id)).thenReturn(commonDict);

        // Then
        assertEquals(commonDict, commondictmapper.getCommonDictById(id));
    }

    @Test
    void testCreateCommonDict() {
        // Given
        CommonDict commonDict = new CommonDict();

        // When
        when(mockCommonDictMapper.createCommonDict(commonDict)).thenReturn(1);

        // Then
        assertEquals(1, commondictmapper.createCommonDict(commonDict));
    }

    @Test
    void testUpdateCommonDict() {
        // Given
        CommonDict commonDict = new CommonDict();

        // When
        when(mockCommonDictMapper.updateCommonDict(commonDict)).thenReturn(1);

        // Then
        assertEquals(1, commondictmapper.updateCommonDict(commonDict));
    }

    @Test
    void testDelCommonDictById() {
        // Given
        Long id = 1L;

        // When
        when(mockCommonDictMapper.delCommonDictById(id)).thenReturn(1);

        // Then
        assertEquals(1, commondictmapper.delCommonDictById(id));
    }

    // Add more test methods for other methods in CommonDictMapper
}
