package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDevicePackageUpdateVOTest {

    @InjectMocks
    private BasicBaseDevicePackageUpdateVO basicbasedevicepackageupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeIdNotNull() {
        // Given
        basicbasedevicepackageupdatevo.setTypeId(1L);

        // When
        Long typeId = basicbasedevicepackageupdatevo.getTypeId();

        // Then
        assertThat(typeId).isNotNull();
    }

    @Test
    void testPackageNameNotBlank() {
        // Given
        basicbasedevicepackageupdatevo.setPackageName("Test Package");

        // When
        String packageName = basicbasedevicepackageupdatevo.getPackageName();

        // Then
        assertThat(packageName).isNotBlank();
    }

    @Test
    void testUpgradeContentNotBlank() {
        // Given
        basicbasedevicepackageupdatevo.setUpgradeContent("Upgrade Description");

        // When
        String upgradeContent = basicbasedevicepackageupdatevo.getUpgradeContent();

        // Then
        assertThat(upgradeContent).isNotBlank();
    }

    @Test
    void testPackageVersionNotBlank() {
        // Given
        basicbasedevicepackageupdatevo.setPackageVersion("1.0.0");

        // When
        String packageVersion = basicbasedevicepackageupdatevo.getPackageVersion();

        // Then
        assertThat(packageVersion).isNotBlank();
    }

    @Test
    void testIdNotNull() {
        // Given
        basicbasedevicepackageupdatevo.setId(1L);

        // When
        Long id = basicbasedevicepackageupdatevo.getId();

        // Then
        assertThat(id).isNotNull();
    }
}
