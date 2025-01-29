package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDevicePackageAddVOTest {

    @InjectMocks
    private BasicBaseDevicePackageAddVO basicbasedevicepackageaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeIdNotNull() {
        // Given
        basicbasedevicepackageaddvo.setTypeId(1L);

        // When
        Long typeId = basicbasedevicepackageaddvo.getTypeId();

        // Then
        assertNotNull(typeId);
        assertThat(typeId).isEqualTo(1L);
    }

    @Test
    void testPackageNameNotBlank() {
        // Given
        basicbasedevicepackageaddvo.setPackageName("Test Package");

        // When
        String packageName = basicbasedevicepackageaddvo.getPackageName();

        // Then
        assertNotNull(packageName);
        assertThat(packageName).isNotBlank();
        assertThat(packageName).isEqualTo("Test Package");
    }

    @Test
    void testUpgradeContentNotBlank() {
        // Given
        basicbasedevicepackageaddvo.setUpgradeContent("Upgrade Description");

        // When
        String upgradeContent = basicbasedevicepackageaddvo.getUpgradeContent();

        // Then
        assertNotNull(upgradeContent);
        assertThat(upgradeContent).isNotBlank();
        assertThat(upgradeContent).isEqualTo("Upgrade Description");
    }

    @Test
    void testPackageVersionNotBlank() {
        // Given
        basicbasedevicepackageaddvo.setPackageVersion("1.0.0");

        // When
        String packageVersion = basicbasedevicepackageaddvo.getPackageVersion();

        // Then
        assertNotNull(packageVersion);
        assertThat(packageVersion).isNotBlank();
        assertThat(packageVersion).isEqualTo("1.0.0");
    }

    // Add more test methods for other methods in BasicBaseDevicePackageAddVO
}
