package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDevicecommandPackageTest {

    @InjectMocks
    private BasicBaseDevicecommandPackage basicbasedevicecommandpackage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long typeId = 2L;
        Long commandId = 3L;
        Long packageId = 4L;
        String remark = "Test Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 5L;
        Long updateBy = 6L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";
        String tenantId = "TenantId";
        String md5 = "md5hash";

        // When
        basicbasedevicecommandpackage.setId(id);
        basicbasedevicecommandpackage.setTypeId(typeId);
        basicbasedevicecommandpackage.setCommandId(commandId);
        basicbasedevicecommandpackage.setPackageId(packageId);
        basicbasedevicecommandpackage.setRemark(remark);
        basicbasedevicecommandpackage.setCreateTime(createTime);
        basicbasedevicecommandpackage.setUpdateTime(updateTime);
        basicbasedevicecommandpackage.setDeleteTime(deleteTime);
        basicbasedevicecommandpackage.setCreateBy(createBy);
        basicbasedevicecommandpackage.setUpdateBy(updateBy);
        basicbasedevicecommandpackage.setOperatorOrgCode(operatorOrgCode);
        basicbasedevicecommandpackage.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasedevicecommandpackage.setTenantId(tenantId);
        basicbasedevicecommandpackage.setMd5(md5);

        // Then
        assertThat(basicbasedevicecommandpackage.getId()).isEqualTo(id);
        assertThat(basicbasedevicecommandpackage.getTypeId()).isEqualTo(typeId);
        assertThat(basicbasedevicecommandpackage.getCommandId()).isEqualTo(commandId);
        assertThat(basicbasedevicecommandpackage.getPackageId()).isEqualTo(packageId);
        assertThat(basicbasedevicecommandpackage.getRemark()).isEqualTo(remark);
        assertThat(basicbasedevicecommandpackage.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasedevicecommandpackage.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasedevicecommandpackage.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasedevicecommandpackage.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasedevicecommandpackage.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasedevicecommandpackage.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasedevicecommandpackage.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasedevicecommandpackage.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasedevicecommandpackage.getMd5()).isEqualTo(md5);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDevicecommandPackage anotherPackage = new BasicBaseDevicecommandPackage();
        anotherPackage.setId(1L);
        anotherPackage.setTypeId(2L);
        anotherPackage.setCommandId(3L);
        anotherPackage.setPackageId(4L);
        anotherPackage.setRemark("Test Remark");
        anotherPackage.setCreateTime(System.currentTimeMillis());
        anotherPackage.setUpdateTime(System.currentTimeMillis());
        anotherPackage.setDeleteTime(System.currentTimeMillis());
        anotherPackage.setCreateBy(5L);
        anotherPackage.setUpdateBy(6L);
        anotherPackage.setOperatorOrgCode("OrgCode");
        anotherPackage.setOperatorSelfOrgCode("SelfOrgCode");
        anotherPackage.setTenantId("TenantId");
        anotherPackage.setMd5("md5hash");

        // When
        basicbasedevicecommandpackage.setId(1L);
        basicbasedevicecommandpackage.setTypeId(2L);
        basicbasedevicecommandpackage.setCommandId(3L);
        basicbasedevicecommandpackage.setPackageId(4L);
        basicbasedevicecommandpackage.setRemark("Test Remark");
        basicbasedevicecommandpackage.setCreateTime(System.currentTimeMillis());
        basicbasedevicecommandpackage.setUpdateTime(System.currentTimeMillis());
        basicbasedevicecommandpackage.setDeleteTime(System.currentTimeMillis());
        basicbasedevicecommandpackage.setCreateBy(5L);
        basicbasedevicecommandpackage.setUpdateBy(6L);
        basicbasedevicecommandpackage.setOperatorOrgCode("OrgCode");
        basicbasedevicecommandpackage.setOperatorSelfOrgCode("SelfOrgCode");
        basicbasedevicecommandpackage.setTenantId("TenantId");
        basicbasedevicecommandpackage.setMd5("md5hash");

        // Then
        assertThat(basicbasedevicecommandpackage).isEqualTo(anotherPackage);
        assertThat(basicbasedevicecommandpackage.hashCode()).isEqualTo(anotherPackage.hashCode());
    }

    // Add more test methods for other methods in BasicBaseDevicecommandPackage
}
