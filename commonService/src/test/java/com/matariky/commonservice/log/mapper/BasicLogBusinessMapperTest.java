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

import com.matariky.commonservice.log.bean.BasicLogBusiness;

@SpringBootTest
public class BasicLogBusinessMapperTest {

    @InjectMocks
    private BasicLogBusinessMapper basicLogBusinessMapper;

    @Mock
    private BasicLogBusinessMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogBusinessAll() {
        // Given
        List<BasicLogBusiness> expectedList = Collections.emptyList();
        when(mockMapper.getBasicLogBusinessAll()).thenReturn(expectedList);

        // When
        List<BasicLogBusiness> result = basicLogBusinessMapper.getBasicLogBusinessAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicLogBusiness() {
        // Given
        BasicLogBusiness bean = new BasicLogBusiness();
        when(mockMapper.createBasicLogBusiness(bean)).thenReturn(1);

        // When
        int result = basicLogBusinessMapper.createBasicLogBusiness(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogBusiness() {
        // Given
        BasicLogBusiness bean = new BasicLogBusiness();
        when(mockMapper.updateBasicLogBusiness(bean)).thenReturn(1);

        // When
        int result = basicLogBusinessMapper.updateBasicLogBusiness(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogBusinessById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicLogBusinessById(id)).thenReturn(1);

        // When
        int result = basicLogBusinessMapper.delBasicLogBusinessById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogBusinessById() {
        // Given
        int id = 1;
        BasicLogBusiness expected = new BasicLogBusiness();
        when(mockMapper.getBasicLogBusinessById(id)).thenReturn(expected);

        // When
        BasicLogBusiness result = basicLogBusinessMapper.getBasicLogBusinessById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicLogBusinessDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicLogBusiness> expectedList = Collections.emptyList();
        when(mockMapper.getBasicLogBusinessDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogBusiness> result = basicLogBusinessMapper.getBasicLogBusinessDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogBusinessDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 0L;
        when(mockMapper.getBasicLogBusinessDACCount(params)).thenReturn(expectedCount);

        // When
        Long result = basicLogBusinessMapper.getBasicLogBusinessDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }

    // Add more test methods for other methods in BasicLogBusinessMapper
}
