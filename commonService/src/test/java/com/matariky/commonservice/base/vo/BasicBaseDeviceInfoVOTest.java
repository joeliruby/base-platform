package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceInfoVOTest {

    @InjectMocks
    private BasicBaseDeviceInfoVO basicbasedeviceinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        basicbasedeviceinfovo.setId(1L);

        // When
        Long id = basicbasedeviceinfovo.getId();

        // Then
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void testGetDeviceCode() {
        // Given
        basicbasedeviceinfovo.setDeviceCode("ABC123");

        // When
        String deviceCode = basicbasedeviceinfovo.getDeviceCode();

        // Then
        assertThat(deviceCode).isEqualTo("ABC123");
    }

    @Test
    void testGetDeviceIp() {
        // Given
        basicbasedeviceinfovo.setDeviceIp("192.168.1.1");

        // When
        String deviceIp = basicbasedeviceinfovo.getDeviceIp();

        // Then
        assertThat(deviceIp).isEqualTo("192.168.1.1");
    }

    @Test
    void testGetDeviceMac() {
        // Given
        basicbasedeviceinfovo.setDeviceMac("00:1A:2B:3C:4D:5E");

        // When
        String deviceMac = basicbasedeviceinfovo.getDeviceMac();

        // Then
        assertThat(deviceMac).isEqualTo("00:1A:2B:3C:4D:5E");
    }

    @Test
    void testGetLongitude() {
        // Given
        basicbasedeviceinfovo.setLongitude("123.456");

        // When
        String longitude = basicbasedeviceinfovo.getLongitude();

        // Then
        assertThat(longitude).isEqualTo("123.456");
    }

    @Test
    void testGetLatitude() {
        // Given
        basicbasedeviceinfovo.setLatitude("78.910");

        // When
        String latitude = basicbasedeviceinfovo.getLatitude();

        // Then
        assertThat(latitude).isEqualTo("78.910");
    }

    @Test
    void testGetInstallAddress() {
        // Given
        basicbasedeviceinfovo.setInstallAddress("123 Main St");

        // When
        String installAddress = basicbasedeviceinfovo.getInstallAddress();

        // Then
        assertThat(installAddress).isEqualTo("123 Main St");
    }

    @Test
    void testGetStatus() {
        // Given
        basicbasedeviceinfovo.setStatus("online");

        // When
        String status = basicbasedeviceinfovo.getStatus();

        // Then
        assertThat(status).isEqualTo("online");
    }

    // Add more test methods for other fields in BasicBaseDeviceInfoVO
}
