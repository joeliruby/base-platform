package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBasePcVersionListVOTest {

    @InjectMocks
    private BasicBasePcVersionListVO basicbasepcversionlistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String versionName = "v1.0";
        String versionNo = "1.0.0";
        String versionContent = "Initial release";
        Long requirementDate = 1627849200000L;
        String messageShutdownTime = "10:00 PM";
        String remark = "First version";
        Long createTime = 1627849200000L;
        Long updateTime = 1627849200000L;
        Long deleteTime = 1627849200000L;
        Long createBy = 1L;
        Long updateBy = 1L;
        String operatorOrgCode = "ORG001";
        String operatorSelfOrgCode = "ORG002";
        String tenantId = "TENANT001";
        String realName = "John Doe";

        // When
        basicbasepcversionlistvo.setId(id);
        basicbasepcversionlistvo.setVersionName(versionName);
        basicbasepcversionlistvo.setVersionNo(versionNo);
        basicbasepcversionlistvo.setVersionContent(versionContent);
        basicbasepcversionlistvo.setRequirementDate(requirementDate);
        basicbasepcversionlistvo.setMessageShutdownTime(messageShutdownTime);
        basicbasepcversionlistvo.setRemark(remark);
        basicbasepcversionlistvo.setCreateTime(createTime);
        basicbasepcversionlistvo.setUpdateTime(updateTime);
        basicbasepcversionlistvo.setDeleteTime(deleteTime);
        basicbasepcversionlistvo.setCreateBy(createBy);
        basicbasepcversionlistvo.setUpdateBy(updateBy);
        basicbasepcversionlistvo.setOperatorOrgCode(operatorOrgCode);
        basicbasepcversionlistvo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasepcversionlistvo.setTenantId(tenantId);
        basicbasepcversionlistvo.setRealName(realName);

        // Then
        assertThat(basicbasepcversionlistvo.getId()).isEqualTo(id);
        assertThat(basicbasepcversionlistvo.getVersionName()).isEqualTo(versionName);
        assertThat(basicbasepcversionlistvo.getVersionNo()).isEqualTo(versionNo);
        assertThat(basicbasepcversionlistvo.getVersionContent()).isEqualTo(versionContent);
        assertThat(basicbasepcversionlistvo.getRequirementDate()).isEqualTo(requirementDate);
        assertThat(basicbasepcversionlistvo.getMessageShutdownTime()).isEqualTo(messageShutdownTime);
        assertThat(basicbasepcversionlistvo.getRemark()).isEqualTo(remark);
        assertThat(basicbasepcversionlistvo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasepcversionlistvo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasepcversionlistvo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasepcversionlistvo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasepcversionlistvo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasepcversionlistvo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasepcversionlistvo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasepcversionlistvo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasepcversionlistvo.getRealName()).isEqualTo(realName);
    }

    @Test
    void testToString() {
        // Given
        basicbasepcversionlistvo.setId(1L);
        basicbasepcversionlistvo.setVersionName("v1.0");

        // When
        String result = basicbasepcversionlistvo.toString();

        // Then
        assertThat(result).contains("BasicBasePcVersionListVO");
        assertThat(result).contains("id=1");
        assertThat(result).contains("versionName=v1.0");
    }

    // Add more test methods for other methods in BasicBasePcVersionListVO
}
