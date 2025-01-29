package com.matariky.commonservice.base.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceTypeInfoTest {

    @InjectMocks
    private BasicBaseDeviceTypeInfo basicbasedevicetypeinfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasedevicetypeinfo.setId(expectedId);

        // When
        Long actualId = basicbasedevicetypeinfo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTypeCode() {
        // Given
        String expectedTypeCode = "TYPE_CODE";
        basicbasedevicetypeinfo.setTypeCode(expectedTypeCode);

        // When
        String actualTypeCode = basicbasedevicetypeinfo.getTypeCode();

        // Then
        assertThat(actualTypeCode).isEqualTo(expectedTypeCode);
    }

    @Test
    void testGetTypeName() {
        // Given
        String expectedTypeName = "TYPE_NAME";
        basicbasedevicetypeinfo.setTypeName(expectedTypeName);

        // When
        String actualTypeName = basicbasedevicetypeinfo.getTypeName();

        // Then
        assertThat(actualTypeName).isEqualTo(expectedTypeName);
    }

    @Test
    void testGetDeviceFactory() {
        // Given
        String expectedDeviceFactory = "DEVICE_FACTORY";
        basicbasedevicetypeinfo.setDeviceFactory(expectedDeviceFactory);

        // When
        String actualDeviceFactory = basicbasedevicetypeinfo.getDeviceFactory();

        // Then
        assertThat(actualDeviceFactory).isEqualTo(expectedDeviceFactory);
    }

    @Test
    void testGetDeviceModel() {
        // Given
        String expectedDeviceModel = "DEVICE_MODEL";
        basicbasedevicetypeinfo.setDeviceModel(expectedDeviceModel);

        // When
        String actualDeviceModel = basicbasedevicetypeinfo.getDeviceModel();

        // Then
        assertThat(actualDeviceModel).isEqualTo(expectedDeviceModel);
    }

    @Test
    void testGetDeviceDescribe() {
        // Given
        String expectedDeviceDescribe = "DEVICE_DESCRIBE";
        basicbasedevicetypeinfo.setDeviceDescribe(expectedDeviceDescribe);

        // When
        String actualDeviceDescribe = basicbasedevicetypeinfo.getDeviceDescribe();

        // Then
        assertThat(actualDeviceDescribe).isEqualTo(expectedDeviceDescribe);
    }

    @Test
    void testGetCurrentVersion() {
        // Given
        String expectedCurrentVersion = "1.0.0";
        basicbasedevicetypeinfo.setCurrentVersion(expectedCurrentVersion);

        // When
        String actualCurrentVersion = basicbasedevicetypeinfo.getCurrentVersion();

        // Then
        assertThat(actualCurrentVersion).isEqualTo(expectedCurrentVersion);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "REMARK";
        basicbasedevicetypeinfo.setRemark(expectedRemark);

        // When
        String actualRemark = basicbasedevicetypeinfo.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basicbasedevicetypeinfo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbasedevicetypeinfo.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        basicbasedevicetypeinfo.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbasedevicetypeinfo.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;
        basicbasedevicetypeinfo.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbasedevicetypeinfo.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;
        basicbasedevicetypeinfo.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbasedevicetypeinfo.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 2L;
        basicbasedevicetypeinfo.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbasedevicetypeinfo.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG_CODE";
        basicbasedevicetypeinfo.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbasedevicetypeinfo.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELF_ORG_CODE";
        basicbasedevicetypeinfo.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbasedevicetypeinfo.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT_ID";
        basicbasedevicetypeinfo.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicbasedevicetypeinfo.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetTypeKey() {
        // Given
        String expectedTypeKey = "TYPE_KEY";
        basicbasedevicetypeinfo.setTypeKey(expectedTypeKey);

        // When
        String actualTypeKey = basicbasedevicetypeinfo.getTypeKey();

        // Then
        assertThat(actualTypeKey).isEqualTo(expectedTypeKey);
    }

    @Test
    void testGetUpgradeTime() {
        // Given
        String expectedUpgradeTime = "UPGRADE_TIME";
        basicbasedevicetypeinfo.setUpgradeTime(expectedUpgradeTime);

        // When
        String actualUpgradeTime = basicbasedevicetypeinfo.getUpgradeTime();

        // Then
        assertThat(actualUpgradeTime).isEqualTo(expectedUpgradeTime);
    }

    @Test
    void testGetIsAutoUpgrade() {
        // Given
        String expectedIsAutoUpgrade = "YES";
        basicbasedevicetypeinfo.setIsAutoUpgrade(expectedIsAutoUpgrade);

        // When
        String actualIsAutoUpgrade = basicbasedevicetypeinfo.getIsAutoUpgrade();

        // Then
        assertThat(actualIsAutoUpgrade).isEqualTo(expectedIsAutoUpgrade);
    }

    @Test
    void testGetStatus() {
        // Given
        Integer expectedStatus = 1;
        basicbasedevicetypeinfo.setStatus(expectedStatus);

        // When
        Integer actualStatus = basicbasedevicetypeinfo.getStatus();

        // Then
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    @Test
    void testGetUpgradeType() {
        // Given
        String expectedUpgradeType = "UPGRADE_TYPE";
        basicbasedevicetypeinfo.setUpgradeType(expectedUpgradeType);

        // When
        String actualUpgradeType = basicbasedevicetypeinfo.getUpgradeType();

        // Then
        assertThat(actualUpgradeType).isEqualTo(expectedUpgradeType);
    }

    @Test
    void testGetProtocolType() {
        // Given
        String expectedProtocolType = "PROTOCOL_TYPE";
        basicbasedevicetypeinfo.setProtocolType(expectedProtocolType);

        // When
        String actualProtocolType = basicbasedevicetypeinfo.getProtocolType();

        // Then
        assertThat(actualProtocolType).isEqualTo(expectedProtocolType);
    }

    @Test
    void testGetCommandList() {
        // Given
        List<BasicBaseDevicecommandVO> expectedCommandList = List.of(new BasicBaseDevicecommandVO());
        basicbasedevicetypeinfo.setCommandList(expectedCommandList);

        // When
        List<BasicBaseDevicecommandVO> actualCommandList = basicbasedevicetypeinfo.getCommandList();

        // Then
        assertThat(actualCommandList).isEqualTo(expectedCommandList);
    }
}
