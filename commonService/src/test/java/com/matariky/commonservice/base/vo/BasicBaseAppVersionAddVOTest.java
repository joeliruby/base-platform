package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseAppVersionAddVOTest {

    @InjectMocks
    private BasicBaseAppVersionAddVO basicbaseappversionaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVersionName() {
        // Given
        String versionName = "1.0.0";
        basicbaseappversionaddvo.setVersionName(versionName);

        // When
        String result = basicbaseappversionaddvo.getVersionName();

        // Then
        assertThat(result).isEqualTo(versionName);
    }

    @Test
    void testVersionNo() {
        // Given
        String versionNo = "100";
        basicbaseappversionaddvo.setVersionNo(versionNo);

        // When
        String result = basicbaseappversionaddvo.getVersionNo();

        // Then
        assertThat(result).isEqualTo(versionNo);
    }

    @Test
    void testUpgradeType() {
        // Given
        String upgradeType = "Major";
        basicbaseappversionaddvo.setUpgradeType(upgradeType);

        // When
        String result = basicbaseappversionaddvo.getUpgradeType();

        // Then
        assertThat(result).isEqualTo(upgradeType);
    }

    @Test
    void testVersionContent() {
        // Given
        String versionContent = "Initial release";
        basicbaseappversionaddvo.setVersionContent(versionContent);

        // When
        String result = basicbaseappversionaddvo.getVersionContent();

        // Then
        assertThat(result).isEqualTo(versionContent);
    }

    @Test
    void testIsForceUpdates() {
        // Given
        Integer isForceUpdates = 1;
        basicbaseappversionaddvo.setIsForceUpdates(isForceUpdates);

        // When
        Integer result = basicbaseappversionaddvo.getIsForceUpdates();

        // Then
        assertThat(result).isEqualTo(isForceUpdates);
    }
}
