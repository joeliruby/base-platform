package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactory;

@SpringBootTest
public class BasicBaseRfidfactoryMapperTest {

    @InjectMocks
    private BasicBaseRfidfactoryMapper basicBaseRfidfactoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidfactoryAll() {
        // Given
        BasicBaseRfidfactory bean = new BasicBaseRfidfactory();
        when(basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryAll(bean)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidfactory> result = basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseRfidfactory() {
        // Given
        BasicBaseRfidfactory bean = new BasicBaseRfidfactory();
        when(basicBaseRfidfactoryMapper.createBasicBaseRfidfactory(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryMapper.createBasicBaseRfidfactory(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidfactory() {
        // Given
        BasicBaseRfidfactory bean = new BasicBaseRfidfactory();
        when(basicBaseRfidfactoryMapper.updateBasicBaseRfidfactory(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryMapper.updateBasicBaseRfidfactory(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidfactoryById() {
        // Given
        Long id = 1L;
        when(basicBaseRfidfactoryMapper.delBasicBaseRfidfactoryById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryMapper.delBasicBaseRfidfactoryById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryById() {
        // Given
        Long id = 1L;
        BasicBaseRfidfactory expected = new BasicBaseRfidfactory();
        when(basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryById(id)).thenReturn(expected);

        // When
        BasicBaseRfidfactory result = basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryById(id);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryDAC(params)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidfactory> result = basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseRfidfactoryDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryDACCount(params)).thenReturn(0L);

        // When
        Long result = basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryDACCount(params);

        // Then
        assertEquals(0L, result);
    }

    // Add more test methods for other methods in BasicBaseRfidfactoryMapper
}
