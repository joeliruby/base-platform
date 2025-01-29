package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceUpgradeListVOTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeListVO basicbasedeviceupgradelistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicbasedeviceupgradelistvo.setIndex(1);

        // When
        Integer index = basicbasedeviceupgradelistvo.getIndex();

        // Then
        assertThat(index).isEqualTo(1);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicbasedeviceupgradelistvo.setPerPage(10);

        // When
        Integer perPage = basicbasedeviceupgradelistvo.getPerPage();

        // Then
        assertThat(perPage).isEqualTo(10);
    }

    @Test
    void testGetPackageName() {
        // Given
        basicbasedeviceupgradelistvo.setPackageName("Test Package");

        // When
        String packageName = basicbasedeviceupgradelistvo.getPackageName();

        // Then
        assertThat(packageName).isEqualTo("Test Package");
    }

    @Test
    void testGetPackageVersion() {
        // Given
        basicbasedeviceupgradelistvo.setPackageVersion("1.0.0");

        // When
        String packageVersion = basicbasedeviceupgradelistvo.getPackageVersion();

        // Then
        assertThat(packageVersion).isEqualTo("1.0.0");
    }

    @Test
    void testGetUpgradeContent() {
        // Given
        basicbasedeviceupgradelistvo.setUpgradeContent("Upgrade description");

        // When
        String upgradeContent = basicbasedeviceupgradelistvo.getUpgradeContent();

        // Then
        assertThat(upgradeContent).isEqualTo("Upgrade description");
    }

    @Test
    void testGetTypeId() {
        // Given
        basicbasedeviceupgradelistvo.setTypeId(123L);

        // When
        Long typeId = basicbasedeviceupgradelistvo.getTypeId();

        // Then
        assertThat(typeId).isEqualTo(123L);
    }
}
