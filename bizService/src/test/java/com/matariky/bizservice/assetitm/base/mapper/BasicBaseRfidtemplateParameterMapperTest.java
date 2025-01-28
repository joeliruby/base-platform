package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplateParameter;

@SpringBootTest
public class BasicBaseRfidtemplateParameterMapperTest {

    @InjectMocks
    private BasicBaseRfidtemplateParameterMapper basicBaseRfidtemplateParameterMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterAll() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterAll(bean))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidtemplateParameter> result = basicBaseRfidtemplateParameterMapper
                .getBasicBaseRfidtemplateParameterAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseRfidtemplateParameter() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.createBasicBaseRfidtemplateParameter(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateParameterMapper.createBasicBaseRfidtemplateParameter(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidtemplateParameter() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.updateBasicBaseRfidtemplateParameter(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateParameterMapper.updateBasicBaseRfidtemplateParameter(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidtemplateParameterById() {
        // Given
        int id = 1;
        when(basicBaseRfidtemplateParameterMapper.delBasicBaseRfidtemplateParameterById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateParameterMapper.delBasicBaseRfidtemplateParameterById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterById() {
        // Given
        int id = 1;
        BasicBaseRfidtemplateParameter expected = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterById(id)).thenReturn(expected);

        // When
        BasicBaseRfidtemplateParameter result = basicBaseRfidtemplateParameterMapper
                .getBasicBaseRfidtemplateParameterById(id);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterDAC(params))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidtemplateParameter> result = basicBaseRfidtemplateParameterMapper
                .getBasicBaseRfidtemplateParameterDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseRfidtemplateParameterDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterDACCount(params)).thenReturn(0L);

        // When
        Long result = basicBaseRfidtemplateParameterMapper.getBasicBaseRfidtemplateParameterDACCount(params);

        // Then
        assertEquals(0L, result);
    }

    // Add more test methods for other methods in
    // BasicBaseRfidtemplateParameterMapper
}
