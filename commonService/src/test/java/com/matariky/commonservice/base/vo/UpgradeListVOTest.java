package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UpgradeListVOTest {

    @InjectMocks
    private UpgradeListVO upgradelistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpgradeStatus() {
        // Given
        Integer expectedStatus = 1;
        upgradelistvo.setUpgradeStatus(expectedStatus);

        // When
        Integer actualStatus = upgradelistvo.getUpgradeStatus();

        // Then
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    @Test
    void testPackageId() {
        // Given
        Long expectedPackageId = 12345L;
        upgradelistvo.setPackageId(expectedPackageId);

        // When
        Long actualPackageId = upgradelistvo.getPackageId();

        // Then
        assertThat(actualPackageId).isEqualTo(expectedPackageId);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        UpgradeListVO anotherUpgradeListVO = new UpgradeListVO();
        anotherUpgradeListVO.setUpgradeStatus(1);
        anotherUpgradeListVO.setPackageId(12345L);
        upgradelistvo.setUpgradeStatus(1);
        upgradelistvo.setPackageId(12345L);

        // When & Then
        assertThat(upgradelistvo).isEqualTo(anotherUpgradeListVO);
        assertThat(upgradelistvo.hashCode()).isEqualTo(anotherUpgradeListVO.hashCode());
    }

    // Add more test methods for other methods in UpgradeListVO if needed
}
