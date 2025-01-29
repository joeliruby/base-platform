package com.matariky.commonservice.base.mapper;

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

import com.matariky.commonservice.base.bean.BasicBaseFormExtend;

@SpringBootTest
public class BasicBaseFormExtendMapperTest {

    @InjectMocks
    private BasicBaseFormExtendMapper basicbaseformextendmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseFormExtendAll() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicbaseformextendmapper.getBasicBaseFormExtendAll(bean)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseFormExtend> result = basicbaseformextendmapper.getBasicBaseFormExtendAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseFormExtend() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicbaseformextendmapper.createBasicBaseFormExtend(bean)).thenReturn(1);

        // When
        int result = basicbaseformextendmapper.createBasicBaseFormExtend(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseFormExtend() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicbaseformextendmapper.updateBasicBaseFormExtend(bean)).thenReturn(1);

        // When
        int result = basicbaseformextendmapper.updateBasicBaseFormExtend(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseFormExtendById() {
        // Given
        Long id = 1L;
        when(basicbaseformextendmapper.delBasicBaseFormExtendById(id)).thenReturn(1);

        // When
        int result = basicbaseformextendmapper.delBasicBaseFormExtendById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseFormExtendById() {
        // Given
        Long id = 1L;
        BasicBaseFormExtend expected = new BasicBaseFormExtend();
        when(basicbaseformextendmapper.getBasicBaseFormExtendById(id)).thenReturn(expected);

        // When
        BasicBaseFormExtend result = basicbaseformextendmapper.getBasicBaseFormExtendById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseFormExtendDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbaseformextendmapper.getBasicBaseFormExtendDAC(params)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseFormExtend> result = basicbaseformextendmapper.getBasicBaseFormExtendDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseFormExtendDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbaseformextendmapper.getBasicBaseFormExtendDACCount(params)).thenReturn(0L);

        // When
        Long result = basicbaseformextendmapper.getBasicBaseFormExtendDACCount(params);

        // Then
        assertEquals(0L, result);
    }
}
