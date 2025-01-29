package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDevicePackageTest {

    @InjectMocks
    private BasicBaseDevicePackage basicbasedevicepackage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String packageName = "Test Package";
        String packageVersion = "1.0.0";

        // When
        basicbasedevicepackage.setId(id);
        basicbasedevicepackage.setPackageName(packageName);
        basicbasedevicepackage.setPackageVersion(packageVersion);

        // Then
        assertThat(basicbasedevicepackage.getId()).isEqualTo(id);
        assertThat(basicbasedevicepackage.getPackageName()).isEqualTo(packageName);
        assertThat(basicbasedevicepackage.getPackageVersion()).isEqualTo(packageVersion);
    }

    @Test
    void testToString() {
        // Given
        basicbasedevicepackage.setId(1L);
        basicbasedevicepackage.setPackageName("Test Package");

        // When
        String result = basicbasedevicepackage.toString();

        // Then
        assertThat(result).contains("Test Package");
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDevicePackage package1 = new BasicBaseDevicePackage();
        package1.setId(1L);
        BasicBaseDevicePackage package2 = new BasicBaseDevicePackage();
        package2.setId(1L);

        // When & Then
        assertThat(package1).isEqualTo(package2);
        assertThat(package1.hashCode()).isEqualTo(package2.hashCode());
    }

    // Add more test methods for other methods in BasicBaseDevicePackage
}
