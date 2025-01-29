package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DeviceIdVOTest {

    @InjectMocks
    private DeviceIdVO deviceidvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeviceIdGetterSetter() {
        // Given
        Long expectedDeviceId = 123L;

        // When
        deviceidvo.setDeviceId(expectedDeviceId);

        // Then
        assertThat(deviceidvo.getDeviceId()).isEqualTo(expectedDeviceId);
    }

    @Test
    void testDeviceCodeGetterSetter() {
        // Given
        String expectedDeviceCode = "ABC123";

        // When
        deviceidvo.setDeviceCode(expectedDeviceCode);

        // Then
        assertThat(deviceidvo.getDeviceCode()).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testDeviceIdVOConstructor() {
        // Given
        Long expectedDeviceId = 123L;
        String expectedDeviceCode = "ABC123";

        // When
        DeviceIdVO deviceIdVO = new DeviceIdVO();
        deviceIdVO.setDeviceId(expectedDeviceId);
        deviceIdVO.setDeviceCode(expectedDeviceCode);

        // Then
        assertThat(deviceIdVO.getDeviceId()).isEqualTo(expectedDeviceId);
        assertThat(deviceIdVO.getDeviceCode()).isEqualTo(expectedDeviceCode);
    }

    // Add more test methods for other methods in DeviceIdVO if any
}
