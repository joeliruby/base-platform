package com.matariky.commonservice.network.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseNetworkLogTest {

    @InjectMocks
    private BasicBaseNetworkLog basicBaseNetworkLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        Long id = 1L;
        basicBaseNetworkLog.setId(id);
        assertThat(basicBaseNetworkLog.getId()).isEqualTo(id);
    }

    @Test
    void testGetAndSetSystemVersionNumber() {
        String systemVersionNumber = "1.0";
        basicBaseNetworkLog.setSystemVersionNumber(systemVersionNumber);
        assertThat(basicBaseNetworkLog.getSystemVersionNumber()).isEqualTo(systemVersionNumber);
    }

    @Test
    void testGetAndSetDeviceType() {
        String deviceType = "Mobile";
        basicBaseNetworkLog.setDeviceType(deviceType);
        assertThat(basicBaseNetworkLog.getDeviceType()).isEqualTo(deviceType);
    }

    @Test
    void testGetAndSetBusinessModule() {
        String businessModule = "Sales";
        basicBaseNetworkLog.setBusinessModule(businessModule);
        assertThat(basicBaseNetworkLog.getBusinessModule()).isEqualTo(businessModule);
    }

    @Test
    void testGetAndSetBusinessTime() {
        Long businessTime = 123456789L;
        basicBaseNetworkLog.setBusinessTime(businessTime);
        assertThat(basicBaseNetworkLog.getBusinessTime()).isEqualTo(businessTime);
    }

    @Test
    void testGetAndSetSignalLevel() {
        Integer signalLevel = 5;
        basicBaseNetworkLog.setSignalLevel(signalLevel);
        assertThat(basicBaseNetworkLog.getSignalLevel()).isEqualTo(signalLevel);
    }

    @Test
    void testGetAndSetCreateTime() {
        Long createTime = 123456789L;
        basicBaseNetworkLog.setCreateTime(createTime);
        assertThat(basicBaseNetworkLog.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        Long updateTime = 123456789L;
        basicBaseNetworkLog.setUpdateTime(updateTime);
        assertThat(basicBaseNetworkLog.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testGetAndSetDeleteTime() {
        Long deleteTime = 123456789L;
        basicBaseNetworkLog.setDeleteTime(deleteTime);
        assertThat(basicBaseNetworkLog.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testGetAndSetCreateBy() {
        Long createBy = 1L;
        basicBaseNetworkLog.setCreateBy(createBy);
        assertThat(basicBaseNetworkLog.getCreateBy()).isEqualTo(createBy);
    }

    @Test
    void testGetAndSetUpdateBy() {
        Long updateBy = 1L;
        basicBaseNetworkLog.setUpdateBy(updateBy);
        assertThat(basicBaseNetworkLog.getUpdateBy()).isEqualTo(updateBy);
    }

    @Test
    void testGetAndSetOperatorOrgCode() {
        String operatorOrgCode = "ORG123";
        basicBaseNetworkLog.setOperatorOrgCode(operatorOrgCode);
        assertThat(basicBaseNetworkLog.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testGetAndSetOperatorSelfOrgCode() {
        String operatorSelfOrgCode = "SELF123";
        basicBaseNetworkLog.setOperatorSelfOrgCode(operatorSelfOrgCode);
        assertThat(basicBaseNetworkLog.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testGetAndSetTenantId() {
        String tenantId = "TENANT123";
        basicBaseNetworkLog.setTenantId(tenantId);
        assertThat(basicBaseNetworkLog.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testGetAndSetAccessAccount() {
        String accessAccount = "account123";
        basicBaseNetworkLog.setAccessAccount(accessAccount);
        assertThat(basicBaseNetworkLog.getAccessAccount()).isEqualTo(accessAccount);
    }
}
