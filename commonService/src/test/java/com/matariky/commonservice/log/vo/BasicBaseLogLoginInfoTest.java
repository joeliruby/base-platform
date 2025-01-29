package com.matariky.commonservice.log.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseLogLoginInfoTest {

    @InjectMocks
    private BasicBaseLogLoginInfo basicBaseLogLoginInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String accessTerminal = "PC";
        String userName = "testUser";
        String outIp = "192.168.1.1";
        String inIp = "10.0.0.1";
        String loginAddress = "Test Address";
        String browser = "Chrome";
        String operatingSystem = "Windows";
        String operateStatus = "Success";
        Long accessTime = 1622547800000L;
        String remark = "Test Remark";
        Long createTime = 1622547800000L;
        Long updateTime = 1622547800000L;
        Long deleteTime = 1622547800000L;
        Long createBy = 1L;
        Long updateBy = 1L;
        String operatorOrgCode = "ORG001";
        String operatorSelfOrgCode = "ORG002";
        String tenantId = "TENANT001";
        String tenantName = "Tenant Name";

        // When
        basicBaseLogLoginInfo.setId(id);
        basicBaseLogLoginInfo.setAccessTerminal(accessTerminal);
        basicBaseLogLoginInfo.setUserName(userName);
        basicBaseLogLoginInfo.setOutIp(outIp);
        basicBaseLogLoginInfo.setInIp(inIp);
        basicBaseLogLoginInfo.setLoginAddress(loginAddress);
        basicBaseLogLoginInfo.setBrowser(browser);
        basicBaseLogLoginInfo.setOperatingSystem(operatingSystem);
        basicBaseLogLoginInfo.setOperateStatus(operateStatus);
        basicBaseLogLoginInfo.setAccessTime(accessTime);
        basicBaseLogLoginInfo.setRemark(remark);
        basicBaseLogLoginInfo.setCreateTime(createTime);
        basicBaseLogLoginInfo.setUpdateTime(updateTime);
        basicBaseLogLoginInfo.setDeleteTime(deleteTime);
        basicBaseLogLoginInfo.setCreateBy(createBy);
        basicBaseLogLoginInfo.setUpdateBy(updateBy);
        basicBaseLogLoginInfo.setOperatorOrgCode(operatorOrgCode);
        basicBaseLogLoginInfo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicBaseLogLoginInfo.setTenantId(tenantId);
        basicBaseLogLoginInfo.setTenantName(tenantName);

        // Then
        assertThat(basicBaseLogLoginInfo.getId()).isEqualTo(id);
        assertThat(basicBaseLogLoginInfo.getAccessTerminal()).isEqualTo(accessTerminal);
        assertThat(basicBaseLogLoginInfo.getUserName()).isEqualTo(userName);
        assertThat(basicBaseLogLoginInfo.getOutIp()).isEqualTo(outIp);
        assertThat(basicBaseLogLoginInfo.getInIp()).isEqualTo(inIp);
        assertThat(basicBaseLogLoginInfo.getLoginAddress()).isEqualTo(loginAddress);
        assertThat(basicBaseLogLoginInfo.getBrowser()).isEqualTo(browser);
        assertThat(basicBaseLogLoginInfo.getOperatingSystem()).isEqualTo(operatingSystem);
        assertThat(basicBaseLogLoginInfo.getOperateStatus()).isEqualTo(operateStatus);
        assertThat(basicBaseLogLoginInfo.getAccessTime()).isEqualTo(accessTime);
        assertThat(basicBaseLogLoginInfo.getRemark()).isEqualTo(remark);
        assertThat(basicBaseLogLoginInfo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseLogLoginInfo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseLogLoginInfo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseLogLoginInfo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseLogLoginInfo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseLogLoginInfo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseLogLoginInfo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicBaseLogLoginInfo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicBaseLogLoginInfo.getTenantName()).isEqualTo(tenantName);
    }

    @Test
    void testDefaultValues() {
        // Given
        BasicBaseLogLoginInfo defaultLogLoginInfo = new BasicBaseLogLoginInfo();

        // Then
        assertThat(defaultLogLoginInfo.getId()).isNull();
        assertThat(defaultLogLoginInfo.getAccessTerminal()).isNull();
        assertThat(defaultLogLoginInfo.getUserName()).isNull();
        assertThat(defaultLogLoginInfo.getOutIp()).isNull();
        assertThat(defaultLogLoginInfo.getInIp()).isNull();
        assertThat(defaultLogLoginInfo.getLoginAddress()).isNull();
        assertThat(defaultLogLoginInfo.getBrowser()).isNull();
        assertThat(defaultLogLoginInfo.getOperatingSystem()).isNull();
        assertThat(defaultLogLoginInfo.getOperateStatus()).isNull();
        assertThat(defaultLogLoginInfo.getAccessTime()).isNull();
        assertThat(defaultLogLoginInfo.getRemark()).isNull();
        assertThat(defaultLogLoginInfo.getCreateTime()).isNull();
        assertThat(defaultLogLoginInfo.getUpdateTime()).isNull();
        assertThat(defaultLogLoginInfo.getDeleteTime()).isNull();
        assertThat(defaultLogLoginInfo.getCreateBy()).isNull();
        assertThat(defaultLogLoginInfo.getUpdateBy()).isNull();
        assertThat(defaultLogLoginInfo.getOperatorOrgCode()).isNull();
        assertThat(defaultLogLoginInfo.getOperatorSelfOrgCode()).isNull();
        assertThat(defaultLogLoginInfo.getTenantId()).isNull();
        assertThat(defaultLogLoginInfo.getTenantName()).isNull();
    }
}
