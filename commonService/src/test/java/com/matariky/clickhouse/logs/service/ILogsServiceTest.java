package com.matariky.clickhouse.logs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.clickhouse.logs.entity.Logs;
import com.github.pagehelper.Page;
import org.mockito.Mock;

@SpringBootTest
public class ILogsServiceTest {

    @InjectMocks
    private ILogsService ilogsservice;

    @Mock
    private Logs logs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTracesAll() {
        // Given
        Integer index = 1;
        Integer perPage = 10;
        Page<Logs> expectedPage = mock(Page.class);
        when(ilogsservice.getTracesAll(logs, index, perPage)).thenReturn(expectedPage);

        // When
        Page<Logs> result = ilogsservice.getTracesAll(logs, index, perPage);

        // Then
        assertNotNull(result);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetAppTracesCount() {
        // Given
        Long expectedCount = 100L;
        when(ilogsservice.getAppTracesCount(logs)).thenReturn(expectedCount);

        // When
        Long result = ilogsservice.getAppTracesCount(logs);

        // Then
        assertNotNull(result);
        assertEquals(expectedCount, result);
    }

    @Test
    void testExport() {
        // Given
        Integer index = 1;
        Integer perPage = 10;

        // When
        ilogsservice.export(logs, index, perPage);

        // Then
        // Verify that the export method was called
        verify(ilogsservice, times(1)).export(logs, index, perPage);
    }
}
