package com.matariky.commonservice.log.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicLogDeviceTest {

    @InjectMocks
    private BasicLogDevice basiclogdevice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        basiclogdevice.setId(id);
        assertThat(basiclogdevice.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetSystemVersion() {
        String systemVersion = "1.0";
        basiclogdevice.setSystemVersion(systemVersion);
        assertThat(basiclogdevice.getSystemVersion()).isEqualTo(systemVersion);
    }

    @Test
    void testSetAndGetAccessTerminals() {
        String accessTerminals = "Terminal1";
        basiclogdevice.setAccessTerminals(accessTerminals);
        assertThat(basiclogdevice.getAccessTerminals()).isEqualTo(accessTerminals);
    }

    @Test
    void testSetAndGetAccessModule() {
        String accessModule = "Module1";
        basiclogdevice.setAccessModule(accessModule);
        assertThat(basiclogdevice.getAccessModule()).isEqualTo(accessModule);
    }

    @Test
    void testSetAndGetBusinessTime() {
        Long businessTime = 123456789L;
        basiclogdevice.setBusinessTime(businessTime);
        assertThat(basiclogdevice.getBusinessTime()).isEqualTo(businessTime);
    }

    @Test
    void testSetAndGetAccessUrl() {
        String accessUrl = "http://example.com";
        basiclogdevice.setAccessUrl(accessUrl);
        assertThat(basiclogdevice.getAccessUrl()).isEqualTo(accessUrl);
    }

    @Test
    void testSetAndGetAccessInterface() {
        String accessInterface = "Interface1";
        basiclogdevice.setAccessInterface(accessInterface);
        assertThat(basiclogdevice.getAccessInterface()).isEqualTo(accessInterface);
    }

    @Test
    void testSetAndGetPhotoelectricityTime() {
        Long photoelectricityTime = 987654321L;
        basiclogdevice.setPhotoelectricityTime(photoelectricityTime);
        assertThat(basiclogdevice.getPhotoelectricityTime()).isEqualTo(photoelectricityTime);
    }

    @Test
    void testSetAndGetDeviceId() {
        Long deviceId = 123L;
        basiclogdevice.setDeviceId(deviceId);
        assertThat(basiclogdevice.getDeviceId()).isEqualTo(deviceId);
    }

    @Test
    void testSetAndGetDeviceCollectInfo() {
        String deviceCollectInfo = "Info1";
        basiclogdevice.setDeviceCollectInfo(deviceCollectInfo);
        assertThat(basiclogdevice.getDeviceCollectInfo()).isEqualTo(deviceCollectInfo);
    }

    @Test
    void testSetAndGetAccessAccount() {
        String accessAccount = "Account1";
        basiclogdevice.setAccessAccount(accessAccount);
        assertThat(basiclogdevice.getAccessAccount()).isEqualTo(accessAccount);
    }

    @Test
    void testSetAndGetOutIp() {
        String outIp = "192.168.1.1";
        basiclogdevice.setOutIp(outIp);
        assertThat(basiclogdevice.getOutIp()).isEqualTo(outIp);
    }

    @Test
    void testSetAndGetLoginAddress() {
        String loginAddress = "Address1";
        basiclogdevice.setLoginAddress(loginAddress);
        assertThat(basiclogdevice.getLoginAddress()).isEqualTo(loginAddress);
    }

    @Test
    void testSetAndGetLogTime() {
        Long logTime = 123456789L;
        basiclogdevice.setLogTime(logTime);
        assertThat(basiclogdevice.getLogTime()).isEqualTo(logTime);
    }

    @Test
    void testSetAndGetRemark() {
        String remark = "Remark1";
        basiclogdevice.setRemark(remark);
        assertThat(basiclogdevice.getRemark()).isEqualTo(remark);
    }

    @Test
    void testSetAndGetCreateTime() {
        Long createTime = 123456789L;
        basiclogdevice.setCreateTime(createTime);
        assertThat(basiclogdevice.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testSetAndGetUpdateTime() {
        Long updateTime = 987654321L;
        basiclogdevice.setUpdateTime(updateTime);
        assertThat(basiclogdevice.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testSetAndGetDeleteTime() {
        Long deleteTime = 123456789L;
        basiclogdevice.setDeleteTime(deleteTime);
        assertThat(basiclogdevice.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testSetAndGetCreateBy() {
        Long createBy = 1L;
        basiclogdevice.setCreateBy(createBy);
        assertThat(basiclogdevice.getCreateBy()).isEqualTo(createBy);
    }

    @Test
    void testSetAndGetUpdateBy() {
        Long updateBy = 2L;
        basiclogdevice.setUpdateBy(updateBy);
        assertThat(basiclogdevice.getUpdateBy()).isEqualTo(updateBy);
    }

    @Test
    void testSetAndGetOperatorOrgCode() {
        String operatorOrgCode = "OrgCode1";
        basiclogdevice.setOperatorOrgCode(operatorOrgCode);
        assertThat(basiclogdevice.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testSetAndGetOperatorSelfOrgCode() {
        String operatorSelfOrgCode = "SelfOrgCode1";
        basiclogdevice.setOperatorSelfOrgCode(operatorSelfOrgCode);
        assertThat(basiclogdevice.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testSetAndGetTenantId() {
        String tenantId = "Tenant1";
        basiclogdevice.setTenantId(tenantId);
        assertThat(basiclogdevice.getTenantId()).isEqualTo(tenantId);
    }
}
