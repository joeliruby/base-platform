package com.matariky.commonservice.error.mapper;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import org.mockito.Mock;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;

@SpringBootTest
public class BasicBaseErrorLogMapperTest {

    @InjectMocks
    private BasicBaseErrorLogMapper basicBaseErrorLogMapper;

    @Mock
    private BasicBaseErrorLogMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseErrorLogAll() {
        // Given
        BasicBaseErrorLog log = new BasicBaseErrorLog();
        when(mockMapper.getBasicBaseErrorLogAll(log)).thenReturn(Collections.singletonList(log));

        // When
        List<BasicBaseErrorLog> result = basicBaseErrorLogMapper.getBasicBaseErrorLogAll(log);

        // Then
        assertThat(result).isNotEmpty();
        verify(mockMapper, times(1)).getBasicBaseErrorLogAll(log);
    }

    @Test
    void testCreateBasicBaseErrorLog() {
        // Given
        BasicBaseErrorLog log = new BasicBaseErrorLog();
        when(mockMapper.createBasicBaseErrorLog(log)).thenReturn(1);

        // When
        int result = basicBaseErrorLogMapper.createBasicBaseErrorLog(log);

        // Then
        assertThat(result).isEqualTo(1);
        verify(mockMapper, times(1)).createBasicBaseErrorLog(log);
    }

    @Test
    void testUpdateBasicBaseErrorLog() {
        // Given
        BasicBaseErrorLog log = new BasicBaseErrorLog();
        when(mockMapper.updateBasicBaseErrorLog(log)).thenReturn(1);

        // When
        int result = basicBaseErrorLogMapper.updateBasicBaseErrorLog(log);

        // Then
        assertThat(result).isEqualTo(1);
        verify(mockMapper, times(1)).updateBasicBaseErrorLog(log);
    }

    @Test
    void testDelBasicBaseErrorLogById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicBaseErrorLogById(id)).thenReturn(1);

        // When
        int result = basicBaseErrorLogMapper.delBasicBaseErrorLogById(id);

        // Then
        assertThat(result).isEqualTo(1);
        verify(mockMapper, times(1)).delBasicBaseErrorLogById(id);
    }

    @Test
    void testGetBasicBaseErrorLogById() {
        // Given
        int id = 1;
        BasicBaseErrorLog log = new BasicBaseErrorLog();
        when(mockMapper.getBasicBaseErrorLogById(id)).thenReturn(log);

        // When
        BasicBaseErrorLog result = basicBaseErrorLogMapper.getBasicBaseErrorLogById(id);

        // Then
        assertThat(result).isNotNull();
        verify(mockMapper, times(1)).getBasicBaseErrorLogById(id);
    }

    // Add more test methods for other methods in BasicBaseErrorLogMapper
}
