package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseAppVersionListVOTest {

    @InjectMocks
    private BasicBaseAppVersionListVO basicbaseappversionlistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String appName = "TestApp";
        String versionName = "1.0";
        String versionNo = "100";
        String upgradeType = "Major";
        String versionContent = "Initial release";
        String upgradeFile = "upgrade.zip";
        String downloadQrcode = "http://example.com/qrcode";
        Integer isForceUpdates = 1;
        String remark = "No remarks";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "ORG001";
        String operatorSelfOrgCode = "ORG002";
        String tenantId = "TENANT001";
        String realName = "John Doe";

        // When
        basicbaseappversionlistvo.setId(id);
        basicbaseappversionlistvo.setAppName(appName);
        basicbaseappversionlistvo.setVersionName(versionName);
        basicbaseappversionlistvo.setVersionNo(versionNo);
        basicbaseappversionlistvo.setUpgradeType(upgradeType);
        basicbaseappversionlistvo.setVersionContent(versionContent);
        basicbaseappversionlistvo.setUpgradeFile(upgradeFile);
        basicbaseappversionlistvo.setDownloadQrcode(downloadQrcode);
        basicbaseappversionlistvo.setIsForceUpdates(isForceUpdates);
        basicbaseappversionlistvo.setRemark(remark);
        basicbaseappversionlistvo.setCreateTime(createTime);
        basicbaseappversionlistvo.setUpdateTime(updateTime);
        basicbaseappversionlistvo.setDeleteTime(deleteTime);
        basicbaseappversionlistvo.setCreateBy(createBy);
        basicbaseappversionlistvo.setUpdateBy(updateBy);
        basicbaseappversionlistvo.setOperatorOrgCode(operatorOrgCode);
        basicbaseappversionlistvo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbaseappversionlistvo.setTenantId(tenantId);
        basicbaseappversionlistvo.setRealName(realName);

        // Then
        assertThat(basicbaseappversionlistvo.getId()).isEqualTo(id);
        assertThat(basicbaseappversionlistvo.getAppName()).isEqualTo(appName);
        assertThat(basicbaseappversionlistvo.getVersionName()).isEqualTo(versionName);
        assertThat(basicbaseappversionlistvo.getVersionNo()).isEqualTo(versionNo);
        assertThat(basicbaseappversionlistvo.getUpgradeType()).isEqualTo(upgradeType);
        assertThat(basicbaseappversionlistvo.getVersionContent()).isEqualTo(versionContent);
        assertThat(basicbaseappversionlistvo.getUpgradeFile()).isEqualTo(upgradeFile);
        assertThat(basicbaseappversionlistvo.getDownloadQrcode()).isEqualTo(downloadQrcode);
        assertThat(basicbaseappversionlistvo.getIsForceUpdates()).isEqualTo(isForceUpdates);
        assertThat(basicbaseappversionlistvo.getRemark()).isEqualTo(remark);
        assertThat(basicbaseappversionlistvo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbaseappversionlistvo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbaseappversionlistvo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbaseappversionlistvo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbaseappversionlistvo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbaseappversionlistvo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbaseappversionlistvo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbaseappversionlistvo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbaseappversionlistvo.getRealName()).isEqualTo(realName);
    }

    @Test
    void testToString() {
        // Given
        basicbaseappversionlistvo.setId(1L);
        basicbaseappversionlistvo.setAppName("TestApp");

        // When
        String toString = basicbaseappversionlistvo.toString();

        // Then
        assertThat(toString).contains("id=1", "appName=TestApp");
    }

    // Add more test methods for other methods in BasicBaseAppVersionListVO
}
