package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseAppVersionUpdateVOTest {

    @InjectMocks
    private BasicBaseAppVersionUpdateVO basicbaseappversionupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVersionName() {
        // Given
        String versionName = "1.0.0";
        basicbaseappversionupdatevo.setVersionName(versionName);

        // When
        String result = basicbaseappversionupdatevo.getVersionName();

        // Then
        assertThat(result).isEqualTo(versionName);
    }

    @Test
    void testVersionNo() {
        // Given
        String versionNo = "100";
        basicbaseappversionupdatevo.setVersionNo(versionNo);

        // When
        String result = basicbaseappversionupdatevo.getVersionNo();

        // Then
        assertThat(result).isEqualTo(versionNo);
    }

    @Test
    void testUpgradeType() {
        // Given
        String upgradeType = "Major";
        basicbaseappversionupdatevo.setUpgradeType(upgradeType);

        // When
        String result = basicbaseappversionupdatevo.getUpgradeType();

        // Then
        assertThat(result).isEqualTo(upgradeType);
    }

    @Test
    void testVersionContent() {
        // Given
        String versionContent = "Initial release";
        basicbaseappversionupdatevo.setVersionContent(versionContent);

        // When
        String result = basicbaseappversionupdatevo.getVersionContent();

        // Then
        assertThat(result).isEqualTo(versionContent);
    }

    @Test
    void testIsForceUpdates() {
        // Given
        Integer isForceUpdates = 1;
        basicbaseappversionupdatevo.setIsForceUpdates(isForceUpdates);

        // When
        Integer result = basicbaseappversionupdatevo.getIsForceUpdates();

        // Then
        assertThat(result).isEqualTo(isForceUpdates);
    }

    @Test
    void testId() {
        // Given
        Long id = 123L;
        basicbaseappversionupdatevo.setId(id);

        // When
        Long result = basicbaseappversionupdatevo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }
}
