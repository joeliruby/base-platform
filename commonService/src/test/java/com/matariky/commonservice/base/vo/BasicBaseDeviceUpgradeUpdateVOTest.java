package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceUpgradeUpdateVOTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeUpdateVO basicbasedeviceupgradeupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPackageName() {
        // Given
        String packageName = "Test Package";
        basicbasedeviceupgradeupdatevo.setPackageName(packageName);

        // When
        String result = basicbasedeviceupgradeupdatevo.getPackageName();

        // Then
        assertThat(result).isEqualTo(packageName);
    }

    @Test
    void testPackageVersion() {
        // Given
        String packageVersion = "1.0.0";
        basicbasedeviceupgradeupdatevo.setPackageVersion(packageVersion);

        // When
        String result = basicbasedeviceupgradeupdatevo.getPackageVersion();

        // Then
        assertThat(result).isEqualTo(packageVersion);
    }

    @Test
    void testRemark() {
        // Given
        String remark = "This is a remark";
        basicbasedeviceupgradeupdatevo.setRemark(remark);

        // When
        String result = basicbasedeviceupgradeupdatevo.getRemark();

        // Then
        assertThat(result).isEqualTo(remark);
    }

    @Test
    void testId() {
        // Given
        Long id = 123L;
        basicbasedeviceupgradeupdatevo.setId(id);

        // When
        Long result = basicbasedeviceupgradeupdatevo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }
}
