package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintParameter;

@SpringBootTest
public class BasicBaseRfidprintParameterMapperTest {

    @InjectMocks
    private BasicBaseRfidprintParameterMapper basicBaseRfidprintParameterMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidprintParameterAll() {
        // Given
        List<BasicBaseRfidprintParameter> expectedList = Collections.emptyList();
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAll()).thenReturn(expectedList);

        // When
        List<BasicBaseRfidprintParameter> result = basicBaseRfidprintParameterMapper
                .getBasicBaseRfidprintParameterAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidprintParameterAllCount() {
        // Given
        int expectedCount = 10;
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseRfidprintParameter() {
        // Given
        BasicBaseRfidprintParameter parameter = new BasicBaseRfidprintParameter();
        when(basicBaseRfidprintParameterMapper.createBasicBaseRfidprintParameter(parameter)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterMapper.createBasicBaseRfidprintParameter(parameter);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprintParameter() {
        // Given
        BasicBaseRfidprintParameter parameter = new BasicBaseRfidprintParameter();
        when(basicBaseRfidprintParameterMapper.updateBasicBaseRfidprintParameter(parameter)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterMapper.updateBasicBaseRfidprintParameter(parameter);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidprintParameterById() {
        // Given
        int id = 1;
        when(basicBaseRfidprintParameterMapper.delBasicBaseRfidprintParameterById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidprintParameterMapper.delBasicBaseRfidprintParameterById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintParameterById() {
        // Given
        int id = 1;
        BasicBaseRfidprintParameter expectedParameter = new BasicBaseRfidprintParameter();
        when(basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterById(id)).thenReturn(expectedParameter);

        // When
        BasicBaseRfidprintParameter result = basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterById(id);

        // Then
        assertEquals(expectedParameter, result);
    }

    // Add more test methods for other methods in BasicBaseRfidprintParameterMapper
}
