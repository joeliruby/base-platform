package com.matariky.commonservice.printlog.mapper;

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

import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;

@SpringBootTest
public class BasicBasePrintLogMapperTest {

    @InjectMocks
    private BasicBasePrintLogMapper basicbaseprintlogmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBasePrintLogAll() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();
        when(basicbaseprintlogmapper.getBasicBasePrintLogAll(bean)).thenReturn(Collections.emptyList());

        // When
        List<BasicBasePrintLog> result = basicbaseprintlogmapper.getBasicBasePrintLogAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBasePrintLog() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();
        when(basicbaseprintlogmapper.createBasicBasePrintLog(bean)).thenReturn(1);

        // When
        int result = basicbaseprintlogmapper.createBasicBasePrintLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBasePrintLog() {
        // Given
        BasicBasePrintLog bean = new BasicBasePrintLog();
        when(basicbaseprintlogmapper.updateBasicBasePrintLog(bean)).thenReturn(1);

        // When
        int result = basicbaseprintlogmapper.updateBasicBasePrintLog(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBasePrintLogById() {
        // Given
        int id = 1;
        when(basicbaseprintlogmapper.delBasicBasePrintLogById(id)).thenReturn(1);

        // When
        int result = basicbaseprintlogmapper.delBasicBasePrintLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBasePrintLogById() {
        // Given
        int id = 1;
        BasicBasePrintLog expectedLog = new BasicBasePrintLog();
        when(basicbaseprintlogmapper.getBasicBasePrintLogById(id)).thenReturn(expectedLog);

        // When
        BasicBasePrintLog result = basicbaseprintlogmapper.getBasicBasePrintLogById(id);

        // Then
        assertNotNull(result);
        assertEquals(expectedLog, result);
    }

    @Test
    void testGetBasicBasePrintLogDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbaseprintlogmapper.getBasicBasePrintLogDAC(params)).thenReturn(Collections.emptyList());

        // When
        List<BasicBasePrintLog> result = basicbaseprintlogmapper.getBasicBasePrintLogDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBasePrintLogDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbaseprintlogmapper.getBasicBasePrintLogDACCount(params)).thenReturn(0L);

        // When
        Long result = basicbaseprintlogmapper.getBasicBasePrintLogDACCount(params);

        // Then
        assertEquals(0L, result);
    }

    // Add more test methods for other methods in BasicBasePrintLogMapper
}
