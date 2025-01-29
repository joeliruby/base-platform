package com.matariky.commonservice.base.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeviceUpgradeVOTest {

    @InjectMocks
    private DeviceUpgradeVO deviceupgradevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeviceIdList() {
        // Given
        deviceupgradevo.setDeviceIdList(Arrays.asList(1L, 2L, 3L));

        // When
        List<Long> deviceIdList = deviceupgradevo.getDeviceIdList();

        // Then
        assertThat(deviceIdList).containsExactly(1L, 2L, 3L);
    }

    @Test
    void testUpgradeType() {
        // Given
        deviceupgradevo.setUpgradeType("immediate");

        // When
        String upgradeType = deviceupgradevo.getUpgradeType();

        // Then
        assertThat(upgradeType).isEqualTo("immediate");
    }

    @Test
    void testUpgradeTime() {
        // Given
        deviceupgradevo.setUpgradeTime(123456789L);

        // When
        Long upgradeTime = deviceupgradevo.getUpgradeTime();

        // Then
        assertThat(upgradeTime).isEqualTo(123456789L);
    }

    // Add more test methods for other methods in DeviceUpgradeVO
}
