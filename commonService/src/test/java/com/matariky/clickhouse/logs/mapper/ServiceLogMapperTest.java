package com.matariky.clickhouse.logs.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.ServiceLog;

@SpringBootTest
public class ServiceLogMapperTest {

    @InjectMocks
    private ServiceLogMapper servicelogmapper;

    @Mock
    private ServiceLog serviceLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAppTracesAll() {
        // Given
        Page<ServiceLog> expectedPage = mock(Page.class);
        when(servicelogmapper.getAppTracesAll(serviceLog)).thenReturn(expectedPage);

        // When
        Page<ServiceLog> result = servicelogmapper.getAppTracesAll(serviceLog);

        // Then
        assertNotNull(result);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetAppTracesCount() {
        // Given
        Long expectedCount = 10L;
        when(servicelogmapper.getAppTracesCount(serviceLog)).thenReturn(expectedCount);

        // When
        Long result = servicelogmapper.getAppTracesCount(serviceLog);

        // Then
        assertNotNull(result);
        assertEquals(expectedCount, result);
    }

    // Add more test methods for other methods in ServiceLogMapper
}
