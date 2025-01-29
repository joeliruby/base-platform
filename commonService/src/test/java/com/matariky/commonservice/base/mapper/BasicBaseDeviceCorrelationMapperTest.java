package com.matariky.commonservice.base.mapper;

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
import com.matariky.commonservice.base.bean.BasicBaseDeviceCorrelation;

@SpringBootTest
public class BasicBaseDeviceCorrelationMapperTest {

    @InjectMocks
    private BasicBaseDeviceCorrelationMapper basicbasedevicecorrelationmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceCorrelationAll() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        List<BasicBaseDeviceCorrelation> expectedList = Collections.singletonList(bean);
        when(basicbasedevicecorrelationmapper.getBasicBaseDeviceCorrelationAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseDeviceCorrelation> result = basicbasedevicecorrelationmapper
                .getBasicBaseDeviceCorrelationAll(bean);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseDeviceCorrelation() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicbasedevicecorrelationmapper.createBasicBaseDeviceCorrelation(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecorrelationmapper.createBasicBaseDeviceCorrelation(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDeviceCorrelation() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicbasedevicecorrelationmapper.updateBasicBaseDeviceCorrelation(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecorrelationmapper.updateBasicBaseDeviceCorrelation(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceCorrelationById() {
        // Given
        int id = 1;
        when(basicbasedevicecorrelationmapper.delBasicBaseDeviceCorrelationById(id)).thenReturn(1);

        // When
        int result = basicbasedevicecorrelationmapper.delBasicBaseDeviceCorrelationById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDeviceCorrelationById() {
        // Given
        int id = 1;
        BasicBaseDeviceCorrelation expected = new BasicBaseDeviceCorrelation();
        when(basicbasedevicecorrelationmapper.getBasicBaseDeviceCorrelationById(id)).thenReturn(expected);

        // When
        BasicBaseDeviceCorrelation result = basicbasedevicecorrelationmapper.getBasicBaseDeviceCorrelationById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseDeviceCorrelationDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseDeviceCorrelation> expectedList = Collections.singletonList(new BasicBaseDeviceCorrelation());
        when(basicbasedevicecorrelationmapper.getBasicBaseDeviceCorrelationDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseDeviceCorrelation> result = basicbasedevicecorrelationmapper
                .getBasicBaseDeviceCorrelationDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseDeviceCorrelationDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 1L;
        when(basicbasedevicecorrelationmapper.getBasicBaseDeviceCorrelationDACCount(params)).thenReturn(expectedCount);

        // When
        Long result = basicbasedevicecorrelationmapper.getBasicBaseDeviceCorrelationDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }

    // Add more test methods for other methods in BasicBaseDeviceCorrelationMapper
}
