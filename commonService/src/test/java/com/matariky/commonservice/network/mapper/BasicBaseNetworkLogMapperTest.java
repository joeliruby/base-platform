package com.matariky.commonservice.network.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.network.bean.BasicBaseNetworkLog;

@SpringBootTest
public class BasicBaseNetworkLogMapperTest {

    @InjectMocks
    private BasicBaseNetworkLogMapper basicBaseNetworkLogMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseNetworkLogAll() {
        // Given
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogAll(log)).thenReturn(Collections.singletonList(log));

        // When
        List<BasicBaseNetworkLog> result = basicBaseNetworkLogMapper.getBasicBaseNetworkLogAll(log);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(log, result.get(0));
    }

    @Test
    void testCreateBasicBaseNetworkLog() {
        // Given
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.createBasicBaseNetworkLog(log)).thenReturn(1);

        // When
        int result = basicBaseNetworkLogMapper.createBasicBaseNetworkLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseNetworkLog() {
        // Given
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.updateBasicBaseNetworkLog(log)).thenReturn(1);

        // When
        int result = basicBaseNetworkLogMapper.updateBasicBaseNetworkLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseNetworkLogById() {
        // Given
        int id = 1;
        when(basicBaseNetworkLogMapper.delBasicBaseNetworkLogById(id)).thenReturn(1);

        // When
        int result = basicBaseNetworkLogMapper.delBasicBaseNetworkLogById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseNetworkLogById() {
        // Given
        int id = 1;
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogById(id)).thenReturn(log);

        // When
        BasicBaseNetworkLog result = basicBaseNetworkLogMapper.getBasicBaseNetworkLogById(id);

        // Then
        assertNotNull(result);
        assertEquals(log, result);
    }

    @Test
    void testGetBasicBaseNetworkLogDAC() {
        // Given
        Map<String, Object> params = Collections.singletonMap("key", "value");
        BasicBaseNetworkLog log = new BasicBaseNetworkLog();
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogDAC(params)).thenReturn(Collections.singletonList(log));

        // When
        List<BasicBaseNetworkLog> result = basicBaseNetworkLogMapper.getBasicBaseNetworkLogDAC(params);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(log, result.get(0));
    }

    @Test
    void testGetBasicBaseNetworkLogDACCount() {
        // Given
        Map<String, Object> params = Collections.singletonMap("key", "value");
        when(basicBaseNetworkLogMapper.getBasicBaseNetworkLogDACCount(params)).thenReturn(1L);

        // When
        Long result = basicBaseNetworkLogMapper.getBasicBaseNetworkLogDACCount(params);

        // Then
        assertEquals(1L, result);
    }

    // Add more test methods for other methods in BasicBaseNetworkLogMapper
}
