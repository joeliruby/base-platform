package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommand;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDevicecommandMapperTest {

    @InjectMocks
    private BasicBaseDevicecommandMapper basicbasedevicecommandmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDevicecommandAll() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicbasedevicecommandmapper.getBasicBaseDevicecommandAll(bean)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDevicecommand> result = basicbasedevicecommandmapper.getBasicBaseDevicecommandAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDevicecommand() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicbasedevicecommandmapper.createBasicBaseDevicecommand(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandmapper.createBasicBaseDevicecommand(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDevicecommand() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicbasedevicecommandmapper.updateBasicBaseDevicecommand(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandmapper.updateBasicBaseDevicecommand(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDevicecommandById() {
        // Given
        int id = 1;
        when(basicbasedevicecommandmapper.delBasicBaseDevicecommandById(id)).thenReturn(1);

        // When
        int result = basicbasedevicecommandmapper.delBasicBaseDevicecommandById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDevicecommandById() {
        // Given
        int id = 1;
        BasicBaseDevicecommand expected = new BasicBaseDevicecommand();
        when(basicbasedevicecommandmapper.getBasicBaseDevicecommandById(id)).thenReturn(expected);

        // When
        BasicBaseDevicecommand result = basicbasedevicecommandmapper.getBasicBaseDevicecommandById(id);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    // Add more test methods for other methods in BasicBaseDevicecommandMapper
}
