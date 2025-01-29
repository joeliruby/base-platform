package com.matariky.commonservice.upload.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.upload.bean.CommonOss;

@SpringBootTest
public class CommonOssMapperTest {

    @InjectMocks
    private CommonOssMapper commonossmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonOssAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");

        // When
        List<CommonOss> result = commonossmapper.getCommonOssAll(params);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testGetCommonOssAllCount() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");

        // When
        int count = commonossmapper.getCommonOssAllCount(params);

        // Then
        assertTrue(count >= 0);
    }

    @Test
    void testCreateCommonOss() {
        // Given
        CommonOss commonOss = new CommonOss();
        // Initialize commonOss with test data

        // When
        int result = commonossmapper.createCommonOss(commonOss);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonOss() {
        // Given
        CommonOss commonOss = new CommonOss();
        // Initialize commonOss with test data

        // When
        int result = commonossmapper.updateCommonOss(commonOss);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonOssById() {
        // Given
        Long id = 1L;

        // When
        int result = commonossmapper.delCommonOssById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonOssById() {
        // Given
        int id = 1;

        // When
        CommonOss result = commonossmapper.getCommonOssById(id);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    // Add more test methods for other methods in CommonOssMapper
}
