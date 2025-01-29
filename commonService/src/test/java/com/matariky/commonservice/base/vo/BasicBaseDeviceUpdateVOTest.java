package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceUpdateVOTest {

    @InjectMocks
    private BasicBaseDeviceUpdateVO basicbasedeviceupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeIdNotNull() {
        // Given
        basicbasedeviceupdatevo.setTypeId(1L);

        // When
        Long typeId = basicbasedeviceupdatevo.getTypeId();

        // Then
        assertThat(typeId).isNotNull();
        assertThat(typeId).isEqualTo(1L);
    }

    @Test
    void testDeviceCodeNotBlank() {
        // Given
        basicbasedeviceupdatevo.setDeviceCode("Device123");

        // When
        String deviceCode = basicbasedeviceupdatevo.getDeviceCode();

        // Then
        assertThat(deviceCode).isNotBlank();
        assertThat(deviceCode).isEqualTo("Device123");
    }

    @Test
    void testDeviceDescribeSize() {
        // Given
        basicbasedeviceupdatevo.setDeviceDescribe("This is a device description");

        // When
        String deviceDescribe = basicbasedeviceupdatevo.getDeviceDescribe();

        // Then
        assertThat(deviceDescribe).hasSizeLessThanOrEqualTo(500);
        assertThat(deviceDescribe).isEqualTo("This is a device description");
    }

    @Test
    void testDeviceIpSize() {
        // Given
        basicbasedeviceupdatevo.setDeviceIp("192.168.1.1");

        // When
        String deviceIp = basicbasedeviceupdatevo.getDeviceIp();

        // Then
        assertThat(deviceIp).hasSizeLessThanOrEqualTo(50);
        assertThat(deviceIp).isEqualTo("192.168.1.1");
    }

    @Test
    void testDeviceMacSize() {
        // Given
        basicbasedeviceupdatevo.setDeviceMac("00:1A:2B:3C:4D:5E");

        // When
        String deviceMac = basicbasedeviceupdatevo.getDeviceMac();

        // Then
        assertThat(deviceMac).hasSizeLessThanOrEqualTo(200);
        assertThat(deviceMac).isEqualTo("00:1A:2B:3C:4D:5E");
    }

    @Test
    void testGisAddressSize() {
        // Given
        basicbasedeviceupdatevo.setGisAddress("123 Main St");

        // When
        String gisAddress = basicbasedeviceupdatevo.getGisAddress();

        // Then
        assertThat(gisAddress).hasSizeLessThanOrEqualTo(100);
        assertThat(gisAddress).isEqualTo("123 Main St");
    }

    @Test
    void testInstallAddressSize() {
        // Given
        basicbasedeviceupdatevo.setInstallAddress("456 Elm St");

        // When
        String installAddress = basicbasedeviceupdatevo.getInstallAddress();

        // Then
        assertThat(installAddress).hasSizeLessThanOrEqualTo(200);
        assertThat(installAddress).isEqualTo("456 Elm St");
    }

    @Test
    void testIdNotNull() {
        // Given
        basicbasedeviceupdatevo.setId(100L);

        // When
        Long id = basicbasedeviceupdatevo.getId();

        // Then
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(100L);
    }
}
