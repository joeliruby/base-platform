package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactoryParameter;

@SpringBootTest
public class BasicBaseRfidfactoryParameterMapperTest {

    @InjectMocks
    private BasicBaseRfidfactoryParameterMapper basicBaseRfidfactoryParameterMapper;

    @Mock
    private BasicBaseRfidfactoryParameterMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterAll() {
        // Given
        List<BasicBaseRfidfactoryParameter> expectedList = Collections.emptyList();
        when(mockMapper.getBasicBaseRfidfactoryParameterAll()).thenReturn(expectedList);

        // When
        List<BasicBaseRfidfactoryParameter> result = basicBaseRfidfactoryParameterMapper
                .getBasicBaseRfidfactoryParameterAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidfactoryParameter() {
        // Given
        BasicBaseRfidfactoryParameter parameter = new BasicBaseRfidfactoryParameter();
        when(mockMapper.createBasicBaseRfidfactoryParameter(parameter)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryParameterMapper.createBasicBaseRfidfactoryParameter(parameter);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidfactoryParameter() {
        // Given
        BasicBaseRfidfactoryParameter parameter = new BasicBaseRfidfactoryParameter();
        when(mockMapper.updateBasicBaseRfidfactoryParameter(parameter)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryParameterMapper.updateBasicBaseRfidfactoryParameter(parameter);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidfactoryParameterById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseRfidfactoryParameterById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryParameterMapper.delBasicBaseRfidfactoryParameterById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterById() {
        // Given
        Long id = 1L;
        BasicBaseRfidfactoryParameter expectedParameter = new BasicBaseRfidfactoryParameter();
        when(mockMapper.getBasicBaseRfidfactoryParameterById(id)).thenReturn(expectedParameter);

        // When
        BasicBaseRfidfactoryParameter result = basicBaseRfidfactoryParameterMapper
                .getBasicBaseRfidfactoryParameterById(id);

        // Then
        assertEquals(expectedParameter, result);
    }

    // Add more test methods for other methods in
    // BasicBaseRfidfactoryParameterMapper
}
