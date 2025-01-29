package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceTypeInfoVOTest {

    @InjectMocks
    private BasicBaseDeviceTypeInfoVO basicbasedevicetypeinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String typeCode = "TypeCode";
        String typeName = "TypeName";
        String deviceFactory = "DeviceFactory";
        String deviceModel = "DeviceModel";
        String deviceDescribe = "DeviceDescribe";
        String currentVersion = "1.0.0";
        String remark = "Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";
        String tenantId = "TenantId";
        String realName = "RealName";
        String isAutoUpgrade = "Yes";
        String upgradeType = "Type";
        String upgradeTime = "Time";
        Integer status = 1;

        // When
        basicbasedevicetypeinfovo.setId(id);
        basicbasedevicetypeinfovo.setTypeCode(typeCode);
        basicbasedevicetypeinfovo.setTypeName(typeName);
        basicbasedevicetypeinfovo.setDeviceFactory(deviceFactory);
        basicbasedevicetypeinfovo.setDeviceModel(deviceModel);
        basicbasedevicetypeinfovo.setDeviceDescribe(deviceDescribe);
        basicbasedevicetypeinfovo.setCurrentVersion(currentVersion);
        basicbasedevicetypeinfovo.setRemark(remark);
        basicbasedevicetypeinfovo.setCreateTime(createTime);
        basicbasedevicetypeinfovo.setUpdateTime(updateTime);
        basicbasedevicetypeinfovo.setDeleteTime(deleteTime);
        basicbasedevicetypeinfovo.setCreateBy(createBy);
        basicbasedevicetypeinfovo.setUpdateBy(updateBy);
        basicbasedevicetypeinfovo.setOperatorOrgCode(operatorOrgCode);
        basicbasedevicetypeinfovo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasedevicetypeinfovo.setTenantId(tenantId);
        basicbasedevicetypeinfovo.setRealName(realName);
        basicbasedevicetypeinfovo.setIsAutoUpgrade(isAutoUpgrade);
        basicbasedevicetypeinfovo.setUpgradeType(upgradeType);
        basicbasedevicetypeinfovo.setUpgradeTime(upgradeTime);
        basicbasedevicetypeinfovo.setStatus(status);

        // Then
        assertThat(basicbasedevicetypeinfovo.getId()).isEqualTo(id);
        assertThat(basicbasedevicetypeinfovo.getTypeCode()).isEqualTo(typeCode);
        assertThat(basicbasedevicetypeinfovo.getTypeName()).isEqualTo(typeName);
        assertThat(basicbasedevicetypeinfovo.getDeviceFactory()).isEqualTo(deviceFactory);
        assertThat(basicbasedevicetypeinfovo.getDeviceModel()).isEqualTo(deviceModel);
        assertThat(basicbasedevicetypeinfovo.getDeviceDescribe()).isEqualTo(deviceDescribe);
        assertThat(basicbasedevicetypeinfovo.getCurrentVersion()).isEqualTo(currentVersion);
        assertThat(basicbasedevicetypeinfovo.getRemark()).isEqualTo(remark);
        assertThat(basicbasedevicetypeinfovo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasedevicetypeinfovo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasedevicetypeinfovo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasedevicetypeinfovo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasedevicetypeinfovo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasedevicetypeinfovo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasedevicetypeinfovo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasedevicetypeinfovo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasedevicetypeinfovo.getRealName()).isEqualTo(realName);
        assertThat(basicbasedevicetypeinfovo.getIsAutoUpgrade()).isEqualTo(isAutoUpgrade);
        assertThat(basicbasedevicetypeinfovo.getUpgradeType()).isEqualTo(upgradeType);
        assertThat(basicbasedevicetypeinfovo.getUpgradeTime()).isEqualTo(upgradeTime);
        assertThat(basicbasedevicetypeinfovo.getStatus()).isEqualTo(status);
    }

    @Test
    void testToString() {
        // Given
        basicbasedevicetypeinfovo.setId(1L);
        basicbasedevicetypeinfovo.setTypeCode("TypeCode");
        basicbasedevicetypeinfovo.setTypeName("TypeName");

        // When
        String result = basicbasedevicetypeinfovo.toString();

        // Then
        assertThat(result).contains("id=1");
        assertThat(result).contains("typeCode=TypeCode");
        assertThat(result).contains("typeName=TypeName");
    }

    // Add more test methods for other methods in BasicBaseDeviceTypeInfoVO
}
