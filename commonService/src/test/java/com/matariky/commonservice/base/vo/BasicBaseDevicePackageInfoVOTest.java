package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDevicePackageInfoVOTest {

    @InjectMocks
    private BasicBaseDevicePackageInfoVO basicbasedevicepackageinfovo;

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
        basicbasedevicepackageinfovo.setId(id);
        basicbasedevicepackageinfovo.setPackageName(packageName);
        basicbasedevicepackageinfovo.setPackageVersion(packageVersion);

        // Then
        assertThat(basicbasedevicepackageinfovo.getId()).isEqualTo(id);
        assertThat(basicbasedevicepackageinfovo.getPackageName()).isEqualTo(packageName);
        assertThat(basicbasedevicepackageinfovo.getPackageVersion()).isEqualTo(packageVersion);
    }

    @Test
    void testDefaultValues() {
        // Given
        BasicBaseDevicePackageInfoVO defaultVO = new BasicBaseDevicePackageInfoVO();

        // Then
        assertThat(defaultVO.getId()).isNull();
        assertThat(defaultVO.getPackageName()).isNull();
        assertThat(defaultVO.getPackageVersion()).isNull();
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDevicePackageInfoVO vo1 = new BasicBaseDevicePackageInfoVO();
        vo1.setId(1L);
        BasicBaseDevicePackageInfoVO vo2 = new BasicBaseDevicePackageInfoVO();
        vo2.setId(1L);

        // Then
        assertThat(vo1).isEqualTo(vo2);
        assertThat(vo1.hashCode()).isEqualTo(vo2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicbasedevicepackageinfovo.setId(1L);
        basicbasedevicepackageinfovo.setPackageName("Test Package");

        // When
        String toString = basicbasedevicepackageinfovo.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("packageName=Test Package");
    }

    // Add more test methods for other methods in BasicBaseDevicePackageInfoVO
}
