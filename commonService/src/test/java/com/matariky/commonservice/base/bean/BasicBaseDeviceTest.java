package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceTest {

    @InjectMocks
    private BasicBaseDevice basicbasedevice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasedevice.setId(expectedId);

        // When
        Long actualId = basicbasedevice.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetDeviceCode() {
        // Given
        String expectedDeviceCode = "ABC123";
        basicbasedevice.setDeviceCode(expectedDeviceCode);

        // When
        String actualDeviceCode = basicbasedevice.getDeviceCode();

        // Then
        assertThat(actualDeviceCode).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testGetDeviceIp() {
        // Given
        String expectedDeviceIp = "192.168.1.1";
        basicbasedevice.setDeviceIp(expectedDeviceIp);

        // When
        String actualDeviceIp = basicbasedevice.getDeviceIp();

        // Then
        assertThat(actualDeviceIp).isEqualTo(expectedDeviceIp);
    }

    @Test
    void testGetLongitude() {
        // Given
        String expectedLongitude = "123.456";
        basicbasedevice.setLongitude(expectedLongitude);

        // When
        String actualLongitude = basicbasedevice.getLongitude();

        // Then
        assertThat(actualLongitude).isEqualTo(expectedLongitude);
    }

    @Test
    void testGetLatitude() {
        // Given
        String expectedLatitude = "78.910";
        basicbasedevice.setLatitude(expectedLatitude);

        // When
        String actualLatitude = basicbasedevice.getLatitude();

        // Then
        assertThat(actualLatitude).isEqualTo(expectedLatitude);
    }

    // Add more test methods for other getters and setters in BasicBaseDevice
}
