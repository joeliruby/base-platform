package com.matariky.commonservice.log.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import com.matariky.commonservice.log.bean.BasicLogError;
import com.matariky.commonservice.log.mapper.BasicLogErrorMapper;

@SpringBootTest
public class BasicLogErrorServiceTest {

    @InjectMocks
    private BasicLogErrorService basicLogErrorService;

    @Mock
    private BasicLogErrorMapper basicLogErrorMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogErrorAll() {
        // Given
        List<BasicLogError> expectedList = Collections.emptyList();
        when(basicLogErrorMapper.getBasicLogErrorAll()).thenReturn(expectedList);

        // When
        List<BasicLogError> result = basicLogErrorService.getBasicLogErrorAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicLogError() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(basicLogErrorMapper.createBasicLogError(logError)).thenReturn(1);

        // When
        int result = basicLogErrorService.createBasicLogError(logError);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogError() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(basicLogErrorMapper.updateById(logError)).thenReturn(1);

        // When
        int result = basicLogErrorService.updateBasicLogError(logError);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogErrorById() {
        // Given
        int id = 1;
        when(basicLogErrorMapper.delBasicLogErrorById(id)).thenReturn(1);

        // When
        int result = basicLogErrorService.delBasicLogErrorById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogErrorById() {
        // Given
        int id = 1;
        BasicLogError expectedLogError = new BasicLogError();
        when(basicLogErrorMapper.getBasicLogErrorById(id)).thenReturn(expectedLogError);

        // When
        BasicLogError result = basicLogErrorService.getBasicLogErrorById(id);

        // Then
        assertEquals(expectedLogError, result);
    }

    @Test
    void testGetBasicLogErrorDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicLogError> expectedList = Collections.emptyList();
        when(basicLogErrorMapper.getBasicLogErrorDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogError> result = basicLogErrorService.getBasicLogErrorDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogErrorDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicLogErrorMapper.getBasicLogErrorDACCount(params)).thenReturn(1L);

        // When
        Long result = basicLogErrorService.getBasicLogErrorDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
