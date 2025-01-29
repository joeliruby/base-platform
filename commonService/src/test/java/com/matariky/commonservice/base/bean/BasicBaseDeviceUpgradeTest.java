package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceUpgradeTest {

    @InjectMocks
    private BasicBaseDeviceUpgrade basicbasedeviceupgrade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasedeviceupgrade.setId(expectedId);

        // When
        Long actualId = basicbasedeviceupgrade.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetDeviceId() {
        // Given
        Long expectedDeviceId = 2L;
        basicbasedeviceupgrade.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = basicbasedeviceupgrade.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testGetPackageId() {
        // Given
        Long expectedPackageId = 3L;
        basicbasedeviceupgrade.setPackageId(expectedPackageId);

        // When
        Long actualPackageId = basicbasedeviceupgrade.getPackageId();

        // Then
        assertThat(actualPackageId).isEqualTo(expectedPackageId);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "Test Remark";
        basicbasedeviceupgrade.setRemark(expectedRemark);

        // When
        String actualRemark = basicbasedeviceupgrade.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basicbasedeviceupgrade.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbasedeviceupgrade.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        basicbasedeviceupgrade.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbasedeviceupgrade.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123123123L;
        basicbasedeviceupgrade.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbasedeviceupgrade.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 4L;
        basicbasedeviceupgrade.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbasedeviceupgrade.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 5L;
        basicbasedeviceupgrade.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbasedeviceupgrade.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG123";
        basicbasedeviceupgrade.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbasedeviceupgrade.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELF123";
        basicbasedeviceupgrade.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbasedeviceupgrade.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";
        basicbasedeviceupgrade.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicbasedeviceupgrade.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetUpgradeStatus() {
        // Given
        Integer expectedUpgradeStatus = 1;
        basicbasedeviceupgrade.setUpgradeStatus(expectedUpgradeStatus);

        // When
        Integer actualUpgradeStatus = basicbasedeviceupgrade.getUpgradeStatus();

        // Then
        assertThat(actualUpgradeStatus).isEqualTo(expectedUpgradeStatus);
    }

    @Test
    void testGetUpgradeType() {
        // Given
        String expectedUpgradeType = "TypeA";
        basicbasedeviceupgrade.setUpgradeType(expectedUpgradeType);

        // When
        String actualUpgradeType = basicbasedeviceupgrade.getUpgradeType();

        // Then
        assertThat(actualUpgradeType).isEqualTo(expectedUpgradeType);
    }

    @Test
    void testGetUpgradeTime() {
        // Given
        Long expectedUpgradeTime = 123456789L;
        basicbasedeviceupgrade.setUpgradeTime(expectedUpgradeTime);

        // When
        Long actualUpgradeTime = basicbasedeviceupgrade.getUpgradeTime();

        // Then
        assertThat(actualUpgradeTime).isEqualTo(expectedUpgradeTime);
    }

    @Test
    void testGetExecuteTime() {
        // Given
        Long expectedExecuteTime = 987654321L;
        basicbasedeviceupgrade.setExecuteTime(expectedExecuteTime);

        // When
        Long actualExecuteTime = basicbasedeviceupgrade.getExecuteTime();

        // Then
        assertThat(actualExecuteTime).isEqualTo(expectedExecuteTime);
    }

    @Test
    void testGetRowId() {
        // Given
        Long expectedRowId = 6L;
        basicbasedeviceupgrade.setRowId(expectedRowId);

        // When
        Long actualRowId = basicbasedeviceupgrade.getRowId();

        // Then
        assertThat(actualRowId).isEqualTo(expectedRowId);
    }
}
