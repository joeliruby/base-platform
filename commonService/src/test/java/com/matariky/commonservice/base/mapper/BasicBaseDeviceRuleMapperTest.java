package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRule;

@SpringBootTest
public class BasicBaseDeviceRuleMapperTest {

    @InjectMocks
    private BasicBaseDeviceRuleMapper basicBaseDeviceRuleMapper;

    @Mock
    private BasicBaseDeviceRule basicBaseDeviceRule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBasicBaseDevicerule() {
        // Given
        BasicBaseDeviceRule bean = new BasicBaseDeviceRule();
        when(basicBaseDeviceRuleMapper.createBasicBaseDevicerule(bean)).thenReturn(1L);

        // When
        Long result = basicBaseDeviceRuleMapper.createBasicBaseDevicerule(bean);

        // Then
        assertNotNull(result);
        assertEquals(1L, result);
    }

    @Test
    void testUpdateBasicBaseDevicerule() {
        // Given
        BasicBaseDeviceRule bean = new BasicBaseDeviceRule();
        bean.setId(1L);
        when(basicBaseDeviceRuleMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseDeviceRuleMapper.updateById(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDeleteBasicBaseDevicerule() {
        // Given
        Long id = 1L;
        when(basicBaseDeviceRuleMapper.deleteById(id)).thenReturn(1);

        // When
        int result = basicBaseDeviceRuleMapper.deleteById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testFindBasicBaseDeviceruleById() {
        // Given
        Long id = 1L;
        BasicBaseDeviceRule bean = new BasicBaseDeviceRule();
        when(basicBaseDeviceRuleMapper.selectById(id)).thenReturn(bean);

        // When
        BasicBaseDeviceRule result = basicBaseDeviceRuleMapper.selectById(id);

        // Then
        assertNotNull(result);
        assertEquals(bean, result);
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleMapper
}
