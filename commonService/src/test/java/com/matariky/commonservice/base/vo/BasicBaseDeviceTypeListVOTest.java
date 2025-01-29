package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceTypeListVOTest {

    @InjectMocks
    private BasicBaseDeviceTypeListVO basicbasedevicetypelistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicbasedevicetypelistvo.setIndex(1);

        // When
        Integer index = basicbasedevicetypelistvo.getIndex();

        // Then
        assertEquals(1, index);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicbasedevicetypelistvo.setPerPage(10);

        // When
        Integer perPage = basicbasedevicetypelistvo.getPerPage();

        // Then
        assertEquals(10, perPage);
    }

    @Test
    void testGetTypeKey() {
        // Given
        basicbasedevicetypelistvo.setTypeKey("typeKey");

        // When
        String typeKey = basicbasedevicetypelistvo.getTypeKey();

        // Then
        assertEquals("typeKey", typeKey);
    }

    @Test
    void testGetDeviceFactory() {
        // Given
        basicbasedevicetypelistvo.setDeviceFactory("deviceFactory");

        // When
        String deviceFactory = basicbasedevicetypelistvo.getDeviceFactory();

        // Then
        assertEquals("deviceFactory", deviceFactory);
    }

    @Test
    void testGetDeviceModel() {
        // Given
        basicbasedevicetypelistvo.setDeviceModel("deviceModel");

        // When
        String deviceModel = basicbasedevicetypelistvo.getDeviceModel();

        // Then
        assertEquals("deviceModel", deviceModel);
    }

    @Test
    void testGetIsAutoUpgrade() {
        // Given
        basicbasedevicetypelistvo.setIsAutoUpgrade("true");

        // When
        String isAutoUpgrade = basicbasedevicetypelistvo.getIsAutoUpgrade();

        // Then
        assertEquals("true", isAutoUpgrade);
    }

    @Test
    void testGetStatus() {
        // Given
        basicbasedevicetypelistvo.setStatus(1);

        // When
        Integer status = basicbasedevicetypelistvo.getStatus();

        // Then
        assertEquals(1, status);
    }
}
