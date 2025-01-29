package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceListVOTest {

    @InjectMocks
    private BasicBaseDeviceListVO basicbasedevicelistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicbasedevicelistvo.setIndex(1);

        // When
        Integer index = basicbasedevicelistvo.getIndex();

        // Then
        assertThat(index).isEqualTo(1);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicbasedevicelistvo.setPerPage(10);

        // When
        Integer perPage = basicbasedevicelistvo.getPerPage();

        // Then
        assertThat(perPage).isEqualTo(10);
    }

    @Test
    void testGetTypeId() {
        // Given
        basicbasedevicelistvo.setTypeId(100L);

        // When
        Long typeId = basicbasedevicelistvo.getTypeId();

        // Then
        assertThat(typeId).isEqualTo(100L);
    }

    @Test
    void testGetDeviceCode() {
        // Given
        basicbasedevicelistvo.setDeviceCode("ABC123");

        // When
        String deviceCode = basicbasedevicelistvo.getDeviceCode();

        // Then
        assertThat(deviceCode).isEqualTo("ABC123");
    }

    @Test
    void testGetDeviceModel() {
        // Given
        basicbasedevicelistvo.setDeviceModel("ModelX");

        // When
        String deviceModel = basicbasedevicelistvo.getDeviceModel();

        // Then
        assertThat(deviceModel).isEqualTo("ModelX");
    }

    @Test
    void testGetDeviceFactory() {
        // Given
        basicbasedevicelistvo.setDeviceFactory("FactoryY");

        // When
        String deviceFactory = basicbasedevicelistvo.getDeviceFactory();

        // Then
        assertThat(deviceFactory).isEqualTo("FactoryY");
    }

    @Test
    void testGetStatus() {
        // Given
        basicbasedevicelistvo.setStatus("on");

        // When
        String status = basicbasedevicelistvo.getStatus();

        // Then
        assertThat(status).isEqualTo("on");
    }

    @Test
    void testGetTypeKey() {
        // Given
        basicbasedevicelistvo.setTypeKey("Key123");

        // When
        String typeKey = basicbasedevicelistvo.getTypeKey();

        // Then
        assertThat(typeKey).isEqualTo("Key123");
    }

    @Test
    void testGetPackageId() {
        // Given
        basicbasedevicelistvo.setPackageId(200L);

        // When
        Long packageId = basicbasedevicelistvo.getPackageId();

        // Then
        assertThat(packageId).isEqualTo(200L);
    }
}
