package com.matariky.commonservice.base.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceUpgradeResVOTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeResVO basicbasedeviceupgraderesvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetPackageId() {
        // Given
        Long packageId = 123L;

        // When
        basicbasedeviceupgraderesvo.setPackageId(packageId);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getPackageId()).isEqualTo(packageId);
    }

    @Test
    void testSetAndGetRemark() {
        // Given
        String remark = "Test Remark";

        // When
        basicbasedeviceupgraderesvo.setRemark(remark);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getRemark()).isEqualTo(remark);
    }

    @Test
    void testSetAndGetCreateTime() {
        // Given
        Long createTime = System.currentTimeMillis();

        // When
        basicbasedeviceupgraderesvo.setCreateTime(createTime);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testSetAndGetUpdateTime() {
        // Given
        Long updateTime = System.currentTimeMillis();

        // When
        basicbasedeviceupgraderesvo.setUpdateTime(updateTime);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testSetAndGetDeleteTime() {
        // Given
        Long deleteTime = System.currentTimeMillis();

        // When
        basicbasedeviceupgraderesvo.setDeleteTime(deleteTime);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testSetAndGetCreateBy() {
        // Given
        Long createBy = 1L;

        // When
        basicbasedeviceupgraderesvo.setCreateBy(createBy);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getCreateBy()).isEqualTo(createBy);
    }

    @Test
    void testSetAndGetUpdateBy() {
        // Given
        Long updateBy = 2L;

        // When
        basicbasedeviceupgraderesvo.setUpdateBy(updateBy);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getUpdateBy()).isEqualTo(updateBy);
    }

    @Test
    void testSetAndGetOperatorOrgCode() {
        // Given
        String operatorOrgCode = "ORG123";

        // When
        basicbasedeviceupgraderesvo.setOperatorOrgCode(operatorOrgCode);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testSetAndGetOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "SELFORG123";

        // When
        basicbasedeviceupgraderesvo.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testSetAndGetTenantId() {
        // Given
        String tenantId = "TENANT123";

        // When
        basicbasedeviceupgraderesvo.setTenantId(tenantId);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testSetAndGetUpgradeStatus() {
        // Given
        Integer upgradeStatus = 1;

        // When
        basicbasedeviceupgraderesvo.setUpgradeStatus(upgradeStatus);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getUpgradeStatus()).isEqualTo(upgradeStatus);
    }

    @Test
    void testSetAndGetUpgradeType() {
        // Given
        String upgradeType = "TypeA";

        // When
        basicbasedeviceupgraderesvo.setUpgradeType(upgradeType);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getUpgradeType()).isEqualTo(upgradeType);
    }

    @Test
    void testSetAndGetUpgradeTime() {
        // Given
        Long upgradeTime = System.currentTimeMillis();

        // When
        basicbasedeviceupgraderesvo.setUpgradeTime(upgradeTime);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getUpgradeTime()).isEqualTo(upgradeTime);
    }

    @Test
    void testSetAndGetExecuteTime() {
        // Given
        Long executeTime = System.currentTimeMillis();

        // When
        basicbasedeviceupgraderesvo.setExecuteTime(executeTime);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getExecuteTime()).isEqualTo(executeTime);
    }

    @Test
    void testSetAndGetDeviceIdList() {
        // Given
        List<DeviceIdVO> deviceIdList = List.of(new DeviceIdVO(), new DeviceIdVO());

        // When
        basicbasedeviceupgraderesvo.setDeviceIdList(deviceIdList);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getDeviceIdList()).isEqualTo(deviceIdList);
    }

    @Test
    void testSetAndGetRowId() {
        // Given
        Long rowId = 123L;

        // When
        basicbasedeviceupgraderesvo.setRowId(rowId);

        // Then
        assertThat(basicbasedeviceupgraderesvo.getRowId()).isEqualTo(rowId);
    }
}
