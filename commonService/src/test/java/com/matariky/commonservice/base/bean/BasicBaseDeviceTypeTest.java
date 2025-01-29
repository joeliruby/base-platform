package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceTypeTest {

    @InjectMocks
    private BasicBaseDeviceType basicbasedevicetype;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String typeCode = "TYPE_CODE";
        String typeName = "TYPE_NAME";
        String deviceFactory = "DEVICE_FACTORY";
        String deviceModel = "DEVICE_MODEL";
        String deviceDescribe = "DEVICE_DESCRIBE";
        String currentVersion = "CURRENT_VERSION";
        String remark = "REMARK";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 1L;
        String operatorOrgCode = "ORG_CODE";
        String operatorSelfOrgCode = "SELF_ORG_CODE";
        String tenantId = "TENANT_ID";
        String typeKey = "TYPE_KEY";
        String isAutoUpgrade = "YES";
        Integer status = 1;
        String protocolType = "PROTOCOL_TYPE";

        // When
        basicbasedevicetype.setId(id);
        basicbasedevicetype.setTypeCode(typeCode);
        basicbasedevicetype.setTypeName(typeName);
        basicbasedevicetype.setDeviceFactory(deviceFactory);
        basicbasedevicetype.setDeviceModel(deviceModel);
        basicbasedevicetype.setDeviceDescribe(deviceDescribe);
        basicbasedevicetype.setCurrentVersion(currentVersion);
        basicbasedevicetype.setRemark(remark);
        basicbasedevicetype.setCreateTime(createTime);
        basicbasedevicetype.setUpdateTime(updateTime);
        basicbasedevicetype.setDeleteTime(deleteTime);
        basicbasedevicetype.setCreateBy(createBy);
        basicbasedevicetype.setUpdateBy(updateBy);
        basicbasedevicetype.setOperatorOrgCode(operatorOrgCode);
        basicbasedevicetype.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasedevicetype.setTenantId(tenantId);
        basicbasedevicetype.setTypeKey(typeKey);
        basicbasedevicetype.setIsAutoUpgrade(isAutoUpgrade);
        basicbasedevicetype.setStatus(status);
        basicbasedevicetype.setProtocolType(protocolType);

        // Then
        assertThat(basicbasedevicetype.getId()).isEqualTo(id);
        assertThat(basicbasedevicetype.getTypeCode()).isEqualTo(typeCode);
        assertThat(basicbasedevicetype.getTypeName()).isEqualTo(typeName);
        assertThat(basicbasedevicetype.getDeviceFactory()).isEqualTo(deviceFactory);
        assertThat(basicbasedevicetype.getDeviceModel()).isEqualTo(deviceModel);
        assertThat(basicbasedevicetype.getDeviceDescribe()).isEqualTo(deviceDescribe);
        assertThat(basicbasedevicetype.getCurrentVersion()).isEqualTo(currentVersion);
        assertThat(basicbasedevicetype.getRemark()).isEqualTo(remark);
        assertThat(basicbasedevicetype.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasedevicetype.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasedevicetype.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasedevicetype.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasedevicetype.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasedevicetype.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasedevicetype.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasedevicetype.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasedevicetype.getTypeKey()).isEqualTo(typeKey);
        assertThat(basicbasedevicetype.getIsAutoUpgrade()).isEqualTo(isAutoUpgrade);
        assertThat(basicbasedevicetype.getStatus()).isEqualTo(status);
        assertThat(basicbasedevicetype.getProtocolType()).isEqualTo(protocolType);
    }

    @Test
    void testToString() {
        // Given
        basicbasedevicetype.setId(1L);
        basicbasedevicetype.setTypeCode("TYPE_CODE");
        basicbasedevicetype.setTypeName("TYPE_NAME");

        // When
        String toString = basicbasedevicetype.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("typeCode=TYPE_CODE");
        assertThat(toString).contains("typeName=TYPE_NAME");
    }

    // Add more test methods for other methods in BasicBaseDeviceType
}
