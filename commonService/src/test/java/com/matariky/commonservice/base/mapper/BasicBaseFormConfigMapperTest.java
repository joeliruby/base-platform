package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseFormConfig;

@SpringBootTest
public class BasicBaseFormConfigMapperTest {

    @InjectMocks
    private BasicBaseFormConfigMapper basicBaseFormConfigMapper;

    @Mock
    private BasicBaseFormConfigMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseFormConfigAll() {
        // Given
        BasicBaseFormConfig config = new BasicBaseFormConfig();
        when(mockMapper.getBasicBaseFormConfigAll(any())).thenReturn(Collections.singletonList(config));

        // When
        List<BasicBaseFormConfig> result = basicBaseFormConfigMapper.getBasicBaseFormConfigAll(config);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(config, result.get(0));
    }

    @Test
    void testCreateBasicBaseFormConfig() {
        // Given
        BasicBaseFormConfig config = new BasicBaseFormConfig();
        when(mockMapper.createBasicBaseFormConfig(any())).thenReturn(1);

        // When
        int result = basicBaseFormConfigMapper.createBasicBaseFormConfig(config);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseFormConfig() {
        // Given
        BasicBaseFormConfig config = new BasicBaseFormConfig();
        when(mockMapper.updateBasicBaseFormConfig(any())).thenReturn(1);

        // When
        int result = basicBaseFormConfigMapper.updateBasicBaseFormConfig(config);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseFormConfigById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseFormConfigById(anyLong())).thenReturn(1);

        // When
        int result = basicBaseFormConfigMapper.delBasicBaseFormConfigById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseFormConfigById() {
        // Given
        Long id = 1L;
        BasicBaseFormConfig config = new BasicBaseFormConfig();
        when(mockMapper.getBasicBaseFormConfigById(anyLong())).thenReturn(config);

        // When
        BasicBaseFormConfig result = basicBaseFormConfigMapper.getBasicBaseFormConfigById(id);

        // Then
        assertNotNull(result);
        assertEquals(config, result);
    }

    // Add more test methods for other methods in BasicBaseFormConfigMapper
}
