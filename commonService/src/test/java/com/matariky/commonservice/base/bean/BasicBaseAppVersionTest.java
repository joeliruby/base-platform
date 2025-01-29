package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseAppVersionTest {

    @InjectMocks
    private BasicBaseAppVersion basicbaseappversion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbaseappversion.setId(expectedId);

        // When
        Long actualId = basicbaseappversion.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetAppName() {
        // Given
        String expectedAppName = "TestApp";
        basicbaseappversion.setAppName(expectedAppName);

        // When
        String actualAppName = basicbaseappversion.getAppName();

        // Then
        assertThat(actualAppName).isEqualTo(expectedAppName);
    }

    @Test
    void testGetVersionName() {
        // Given
        String expectedVersionName = "1.0.0";
        basicbaseappversion.setVersionName(expectedVersionName);

        // When
        String actualVersionName = basicbaseappversion.getVersionName();

        // Then
        assertThat(actualVersionName).isEqualTo(expectedVersionName);
    }

    @Test
    void testGetVersionNo() {
        // Given
        String expectedVersionNo = "100";
        basicbaseappversion.setVersionNo(expectedVersionNo);

        // When
        String actualVersionNo = basicbaseappversion.getVersionNo();

        // Then
        assertThat(actualVersionNo).isEqualTo(expectedVersionNo);
    }

    @Test
    void testGetUpgradeType() {
        // Given
        String expectedUpgradeType = "Major";
        basicbaseappversion.setUpgradeType(expectedUpgradeType);

        // When
        String actualUpgradeType = basicbaseappversion.getUpgradeType();

        // Then
        assertThat(actualUpgradeType).isEqualTo(expectedUpgradeType);
    }

    @Test
    void testGetVersionContent() {
        // Given
        String expectedVersionContent = "Initial release";
        basicbaseappversion.setVersionContent(expectedVersionContent);

        // When
        String actualVersionContent = basicbaseappversion.getVersionContent();

        // Then
        assertThat(actualVersionContent).isEqualTo(expectedVersionContent);
    }

    @Test
    void testGetUpgradeFile() {
        // Given
        String expectedUpgradeFile = "upgrade.zip";
        basicbaseappversion.setUpgradeFile(expectedUpgradeFile);

        // When
        String actualUpgradeFile = basicbaseappversion.getUpgradeFile();

        // Then
        assertThat(actualUpgradeFile).isEqualTo(expectedUpgradeFile);
    }

    @Test
    void testGetDownloadQrcode() {
        // Given
        String expectedDownloadQrcode = "http://example.com/qrcode";
        basicbaseappversion.setDownloadQrcode(expectedDownloadQrcode);

        // When
        String actualDownloadQrcode = basicbaseappversion.getDownloadQrcode();

        // Then
        assertThat(actualDownloadQrcode).isEqualTo(expectedDownloadQrcode);
    }

    @Test
    void testIsForceUpdates() {
        // Given
        Integer expectedIsForceUpdates = 1;
        basicbaseappversion.setIsForceUpdates(expectedIsForceUpdates);

        // When
        Integer actualIsForceUpdates = basicbaseappversion.getIsForceUpdates();

        // Then
        assertThat(actualIsForceUpdates).isEqualTo(expectedIsForceUpdates);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "No remarks";
        basicbaseappversion.setRemark(expectedRemark);

        // When
        String actualRemark = basicbaseappversion.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 1627849200000L;
        basicbaseappversion.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbaseappversion.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 1627849200000L;
        basicbaseappversion.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbaseappversion.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 1627849200000L;
        basicbaseappversion.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbaseappversion.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;
        basicbaseappversion.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbaseappversion.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 1L;
        basicbaseappversion.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbaseappversion.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG123";
        basicbaseappversion.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbaseappversion.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELF123";
        basicbaseappversion.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbaseappversion.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";
        basicbaseappversion.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicbaseappversion.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }
}
