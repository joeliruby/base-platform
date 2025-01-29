package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpgradeDeviceVOTest {

    @InjectMocks
    private UpgradeDeviceVO upgradedevicevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeviceId() {
        // Given
        Long expectedDeviceId = 123L;
        upgradedevicevo.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = upgradedevicevo.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testDeviceCode() {
        // Given
        String expectedDeviceCode = "ABC123";
        upgradedevicevo.setDeviceCode(expectedDeviceCode);

        // When
        String actualDeviceCode = upgradedevicevo.getDeviceCode();

        // Then
        assertThat(actualDeviceCode).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testDeviceModel() {
        // Given
        String expectedDeviceModel = "ModelX";
        upgradedevicevo.setDeviceModel(expectedDeviceModel);

        // When
        String actualDeviceModel = upgradedevicevo.getDeviceModel();

        // Then
        assertThat(actualDeviceModel).isEqualTo(expectedDeviceModel);
    }

    // Add more test methods for other methods in UpgradeDeviceVO if needed
}
