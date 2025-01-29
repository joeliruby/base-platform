package com.matariky.commonservice.base.mapper;

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

import com.matariky.commonservice.base.bean.BasicBaseDevicecommandPackage;

@SpringBootTest
public class BasicBaseDevicecommandPackageMapperTest {

    @InjectMocks
    private BasicBaseDevicecommandPackageMapper basicbasedevicecommandpackagemapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDevicecommandPackageAll() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicbasedevicecommandpackagemapper.getBasicBaseDevicecommandPackageAll(bean))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDevicecommandPackage> result = basicbasedevicecommandpackagemapper
                .getBasicBaseDevicecommandPackageAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDevicecommandPackage() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicbasedevicecommandpackagemapper.createBasicBaseDevicecommandPackage(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandpackagemapper.createBasicBaseDevicecommandPackage(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDevicecommandPackage() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicbasedevicecommandpackagemapper.updateBasicBaseDevicecommandPackage(bean)).thenReturn(1);

        // When
        int result = basicbasedevicecommandpackagemapper.updateBasicBaseDevicecommandPackage(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDevicecommandPackageById() {
        // Given
        int id = 1;
        when(basicbasedevicecommandpackagemapper.delBasicBaseDevicecommandPackageById(id)).thenReturn(1);

        // When
        int result = basicbasedevicecommandpackagemapper.delBasicBaseDevicecommandPackageById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseDevicecommandPackageById() {
        // Given
        int id = 1;
        BasicBaseDevicecommandPackage expected = new BasicBaseDevicecommandPackage();
        when(basicbasedevicecommandpackagemapper.getBasicBaseDevicecommandPackageById(id)).thenReturn(expected);

        // When
        BasicBaseDevicecommandPackage result = basicbasedevicecommandpackagemapper
                .getBasicBaseDevicecommandPackageById(id);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseDevicecommandPackageDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbasedevicecommandpackagemapper.getBasicBaseDevicecommandPackageDAC(params))
                .thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDevicecommandPackage> result = basicbasedevicecommandpackagemapper
                .getBasicBaseDevicecommandPackageDAC(params);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBasicBaseDevicecommandPackageDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(basicbasedevicecommandpackagemapper.getBasicBaseDevicecommandPackageDACCount(params)).thenReturn(0L);

        // When
        Long result = basicbasedevicecommandpackagemapper.getBasicBaseDevicecommandPackageDACCount(params);

        // Then
        assertEquals(0L, result);
    }
}
