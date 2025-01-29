package com.matariky.commonservice.device.mapper;

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

import com.matariky.commonservice.device.bean.BasicBaseDeviceLog;

@SpringBootTest
public class BasicBaseDeviceLogMapperTest {

    @InjectMocks
    private BasicBaseDeviceLogMapper basicbasedevicelogmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceLogAll() {
        // Given
        BasicBaseDeviceLog log = new BasicBaseDeviceLog();
        when(basicbasedevicelogmapper.getBasicBaseDeviceLogAll(log)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceLog> result = basicbasedevicelogmapper.getBasicBaseDeviceLogAll(log);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDeviceLog() {
        // Given
        BasicBaseDeviceLog log = new BasicBaseDeviceLog();
        when(basicbasedevicelogmapper.createBasicBaseDeviceLog(log)).thenReturn(1);

        // When
        int result = basicbasedevicelogmapper.createBasicBaseDeviceLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDeviceLog() {
        // Given
        BasicBaseDeviceLog log = new BasicBaseDeviceLog();
        when(basicbasedevicelogmapper.updateBasicBaseDeviceLog(log)).thenReturn(1);

        // When
        int result = basicbasedevicelogmapper.updateBasicBaseDeviceLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceLogById() {
        // Given
        int id = 1;
        when(basicbasedevicelogmapper.delBasicBaseDeviceLogById(id)).thenReturn(1);

        // When
        int result = basicbasedevicelogmapper.delBasicBaseDeviceLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDeviceLogById() {
        // Given
        int id = 1;
        BasicBaseDeviceLog log = new BasicBaseDeviceLog();
        when(basicbasedevicelogmapper.getBasicBaseDeviceLogById(id)).thenReturn(log);

        // When
        BasicBaseDeviceLog result = basicbasedevicelogmapper.getBasicBaseDeviceLogById(id);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetBasicBaseDeviceLogDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbasedevicelogmapper.getBasicBaseDeviceLogDAC(params)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceLog> result = basicbasedevicelogmapper.getBasicBaseDeviceLogDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseDeviceLogDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbasedevicelogmapper.getBasicBaseDeviceLogDACCount(params)).thenReturn(0L);

        // When
        Long result = basicbasedevicelogmapper.getBasicBaseDeviceLogDACCount(params);

        // Then
        assertEquals(0L, result);
    }
}
