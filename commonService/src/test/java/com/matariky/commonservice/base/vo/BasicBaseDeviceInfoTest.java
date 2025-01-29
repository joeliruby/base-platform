package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceInfoTest {

    @InjectMocks
    private BasicBaseDeviceInfo basicBaseDeviceInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String deviceCode = "Device123";
        String deviceDescribe = "Test Device";

        // When
        basicBaseDeviceInfo.setId(id);
        basicBaseDeviceInfo.setDeviceCode(deviceCode);
        basicBaseDeviceInfo.setDeviceDescribe(deviceDescribe);

        // Then
        assertThat(basicBaseDeviceInfo.getId()).isEqualTo(id);
        assertThat(basicBaseDeviceInfo.getDeviceCode()).isEqualTo(deviceCode);
        assertThat(basicBaseDeviceInfo.getDeviceDescribe()).isEqualTo(deviceDescribe);
    }

    @Test
    void testDefaultValues() {
        // Given
        BasicBaseDeviceInfo defaultDeviceInfo = new BasicBaseDeviceInfo();

        // Then
        assertThat(defaultDeviceInfo.getId()).isNull();
        assertThat(defaultDeviceInfo.getDeviceCode()).isNull();
        assertThat(defaultDeviceInfo.getDeviceDescribe()).isNull();
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDeviceInfo deviceInfo1 = new BasicBaseDeviceInfo();
        deviceInfo1.setId(1L);
        BasicBaseDeviceInfo deviceInfo2 = new BasicBaseDeviceInfo();
        deviceInfo2.setId(1L);

        // Then
        assertThat(deviceInfo1).isEqualTo(deviceInfo2);
        assertThat(deviceInfo1.hashCode()).isEqualTo(deviceInfo2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicBaseDeviceInfo.setId(1L);
        basicBaseDeviceInfo.setDeviceCode("Device123");

        // When
        String toString = basicBaseDeviceInfo.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("deviceCode=Device123");
    }

    // Add more test methods for other methods in BasicBaseDeviceInfo
}
