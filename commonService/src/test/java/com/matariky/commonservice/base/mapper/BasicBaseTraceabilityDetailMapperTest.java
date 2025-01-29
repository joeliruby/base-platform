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

import com.matariky.commonservice.base.bean.BasicBaseTraceabilityDetail;

@SpringBootTest
public class BasicBaseTraceabilityDetailMapperTest {

    @InjectMocks
    private BasicBaseTraceabilityDetailMapper basicBaseTraceabilityDetailMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseTraceabilityDetailAll() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailAll(bean))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseTraceabilityDetail> result = basicBaseTraceabilityDetailMapper
                .getBasicBaseTraceabilityDetailAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseTraceabilityDetail() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.createBasicBaseTraceabilityDetail(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityDetailMapper.createBasicBaseTraceabilityDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseTraceabilityDetail() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.updateBasicBaseTraceabilityDetail(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityDetailMapper.updateBasicBaseTraceabilityDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseTraceabilityDetailById() {
        // Given
        int id = 1;
        when(basicBaseTraceabilityDetailMapper.delBasicBaseTraceabilityDetailById(id)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityDetailMapper.delBasicBaseTraceabilityDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseTraceabilityDetailById() {
        // Given
        int id = 1;
        BasicBaseTraceabilityDetail expectedDetail = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailById(id)).thenReturn(expectedDetail);

        // When
        BasicBaseTraceabilityDetail result = basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailById(id);

        // Then
        assertNotNull(result);
        assertEquals(expectedDetail, result);
    }

    @Test
    void testGetBasicBaseTraceabilityDetailDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDAC(params))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseTraceabilityDetail> result = basicBaseTraceabilityDetailMapper
                .getBasicBaseTraceabilityDetailDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseTraceabilityDetailDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDACCount(params)).thenReturn(0L);

        // When
        Long result = basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDACCount(params);

        // Then
        assertEquals(0L, result);
    }

    // Add more test methods for other methods in BasicBaseTraceabilityDetailMapper
}
