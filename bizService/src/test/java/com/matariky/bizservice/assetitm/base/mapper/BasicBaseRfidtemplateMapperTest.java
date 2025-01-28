package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplate;

@SpringBootTest
public class BasicBaseRfidtemplateMapperTest {

    @InjectMocks
    private BasicBaseRfidtemplateMapper basicBaseRfidtemplateMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidtemplateAll() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        List<BasicBaseRfidtemplate> expectedList = Collections.singletonList(bean);
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidtemplate> result = basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateAll(bean);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidtemplate() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateMapper.createBasicBaseRfidtemplate(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateMapper.createBasicBaseRfidtemplate(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidtemplate() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateMapper.updateBasicBaseRfidtemplate(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateMapper.updateBasicBaseRfidtemplate(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidtemplateById() {
        // Given
        int id = 1;
        when(basicBaseRfidtemplateMapper.delBasicBaseRfidtemplateById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidtemplateMapper.delBasicBaseRfidtemplateById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateById() {
        // Given
        int id = 1;
        BasicBaseRfidtemplate expectedBean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateById(id)).thenReturn(expectedBean);

        // When
        BasicBaseRfidtemplate result = basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateById(id);

        // Then
        assertEquals(expectedBean, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseRfidtemplate> expectedList = Collections.singletonList(new BasicBaseRfidtemplate());
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidtemplate> result = basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidtemplateDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 1L;
        when(basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDACCount(params)).thenReturn(expectedCount);

        // When
        Long result = basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }
}
