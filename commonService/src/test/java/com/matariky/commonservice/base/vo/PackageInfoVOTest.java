package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PackageInfoVOTest {

    @InjectMocks
    private PackageInfoVO packageinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPackageVersion() {
        // Given
        String expectedVersion = "1.0.0";
        packageinfovo.setPackageVersion(expectedVersion);

        // When
        String actualVersion = packageinfovo.getPackageVersion();

        // Then
        assertThat(actualVersion).isEqualTo(expectedVersion);
    }

    @Test
    void testSetPackageVersion() {
        // Given
        String expectedVersion = "2.0.0";

        // When
        packageinfovo.setPackageVersion(expectedVersion);

        // Then
        assertThat(packageinfovo.getPackageVersion()).isEqualTo(expectedVersion);
    }

    @Test
    void testGetPackageId() {
        // Given
        Long expectedId = 123L;
        packageinfovo.setPackageId(expectedId);

        // When
        Long actualId = packageinfovo.getPackageId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetPackageId() {
        // Given
        Long expectedId = 456L;

        // When
        packageinfovo.setPackageId(expectedId);

        // Then
        assertThat(packageinfovo.getPackageId()).isEqualTo(expectedId);
    }
}
