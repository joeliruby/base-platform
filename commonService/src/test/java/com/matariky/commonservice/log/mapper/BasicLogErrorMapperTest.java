package com.matariky.commonservice.log.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.log.bean.BasicLogError;

@SpringBootTest
public class BasicLogErrorMapperTest {

    @InjectMocks
    private BasicLogErrorMapper basiclogerrormapper;

    @Mock
    private BasicLogErrorMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogErrorAll() {
        // Given
        List<BasicLogError> expectedList = Collections.emptyList();
        when(mockMapper.getBasicLogErrorAll()).thenReturn(expectedList);

        // When
        List<BasicLogError> result = basiclogerrormapper.getBasicLogErrorAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogErrorAllCount() {
        // Given
        int expectedCount = 10;
        when(mockMapper.getBasicLogErrorAllCount()).thenReturn(expectedCount);

        // When
        int result = basiclogerrormapper.getBasicLogErrorAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicLogError() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(mockMapper.createBasicLogError(logError)).thenReturn(1);

        // When
        int result = basiclogerrormapper.createBasicLogError(logError);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogError() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(mockMapper.updateBasicLogError(logError)).thenReturn(1);

        // When
        int result = basiclogerrormapper.updateBasicLogError(logError);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogErrorById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicLogErrorById(id)).thenReturn(1);

        // When
        int result = basiclogerrormapper.delBasicLogErrorById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogErrorById() {
        // Given
        int id = 1;
        BasicLogError expectedLogError = new BasicLogError();
        when(mockMapper.getBasicLogErrorById(id)).thenReturn(expectedLogError);

        // When
        BasicLogError result = basiclogerrormapper.getBasicLogErrorById(id);

        // Then
        assertEquals(expectedLogError, result);
    }

    @Test
    void testGetBasicLogErrorDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicLogError> expectedList = Collections.emptyList();
        when(mockMapper.getBasicLogErrorDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogError> result = basiclogerrormapper.getBasicLogErrorDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogErrorDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        long expectedCount = 10L;
        when(mockMapper.getBasicLogErrorDACCount(params)).thenReturn(expectedCount);

        // When
        long result = basiclogerrormapper.getBasicLogErrorDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }
}
