package com.matariky.commonservice.device.bean;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceLogTest {

    @InjectMocks
    private BasicBaseDeviceLog basicbasedevicelog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long expectedId = 1L;

        // When
        basicbasedevicelog.setId(expectedId);

        // Then
        assertThat(basicbasedevicelog.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetAndSetSystemVersionNumber() {
        // Given
        String expectedSystemVersionNumber = "1.0";

        // When
        basicbasedevicelog.setSystemVersionNumber(expectedSystemVersionNumber);

        // Then
        assertThat(basicbasedevicelog.getSystemVersionNumber()).isEqualTo(expectedSystemVersionNumber);
    }

    @Test
    void testGetAndSetDeviceType() {
        // Given
        String expectedDeviceType = "TypeA";

        // When
        basicbasedevicelog.setDeviceType(expectedDeviceType);

        // Then
        assertThat(basicbasedevicelog.getDeviceType()).isEqualTo(expectedDeviceType);
    }

    @Test
    void testGetAndSetBusinessModule() {
        // Given
        String expectedBusinessModule = "ModuleA";

        // When
        basicbasedevicelog.setBusinessModule(expectedBusinessModule);

        // Then
        assertThat(basicbasedevicelog.getBusinessModule()).isEqualTo(expectedBusinessModule);
    }

    @Test
    void testGetAndSetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;

        // When
        basicbasedevicelog.setBusinessTime(expectedBusinessTime);

        // Then
        assertThat(basicbasedevicelog.getBusinessTime()).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testGetAndSetDeviceCode() {
        // Given
        String expectedDeviceCode = "Device123";

        // When
        basicbasedevicelog.setDeviceCode(expectedDeviceCode);

        // Then
        assertThat(basicbasedevicelog.getDeviceCode()).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testGetAndSetApiName() {
        // Given
        String expectedApiName = "API1";

        // When
        basicbasedevicelog.setApiName(expectedApiName);

        // Then
        assertThat(basicbasedevicelog.getApiName()).isEqualTo(expectedApiName);
    }

    @Test
    void testGetAndSetPower() {
        // Given
        BigDecimal expectedPower = new BigDecimal("100.0");

        // When
        basicbasedevicelog.setPower(expectedPower);

        // Then
        assertThat(basicbasedevicelog.getPower()).isEqualTo(expectedPower);
    }

    @Test
    void testGetAndSetCollectContent() {
        // Given
        String expectedCollectContent = "Content";

        // When
        basicbasedevicelog.setCollectContent(expectedCollectContent);

        // Then
        assertThat(basicbasedevicelog.getCollectContent()).isEqualTo(expectedCollectContent);
    }

    @Test
    void testGetAndSetPhysicalAddress() {
        // Given
        String expectedPhysicalAddress = "Address";

        // When
        basicbasedevicelog.setPhysicalAddress(expectedPhysicalAddress);

        // Then
        assertThat(basicbasedevicelog.getPhysicalAddress()).isEqualTo(expectedPhysicalAddress);
    }

    @Test
    void testGetAndSetAccessAccount() {
        // Given
        String expectedAccessAccount = "Account";

        // When
        basicbasedevicelog.setAccessAccount(expectedAccessAccount);

        // Then
        assertThat(basicbasedevicelog.getAccessAccount()).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testGetAndSetServerIp() {
        // Given
        String expectedServerIp = "192.168.1.1";

        // When
        basicbasedevicelog.setServerIp(expectedServerIp);

        // Then
        assertThat(basicbasedevicelog.getServerIp()).isEqualTo(expectedServerIp);
    }

    @Test
    void testGetAndSetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;

        // When
        basicbasedevicelog.setCreateTime(expectedCreateTime);

        // Then
        assertThat(basicbasedevicelog.getCreateTime()).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;

        // When
        basicbasedevicelog.setUpdateTime(expectedUpdateTime);

        // Then
        assertThat(basicbasedevicelog.getUpdateTime()).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetAndSetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;

        // When
        basicbasedevicelog.setDeleteTime(expectedDeleteTime);

        // Then
        assertThat(basicbasedevicelog.getDeleteTime()).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetAndSetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;

        // When
        basicbasedevicelog.setCreateBy(expectedCreateBy);

        // Then
        assertThat(basicbasedevicelog.getCreateBy()).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetAndSetUpdateBy() {
        // Given
        Long expectedUpdateBy = 2L;

        // When
        basicbasedevicelog.setUpdateBy(expectedUpdateBy);

        // Then
        assertThat(basicbasedevicelog.getUpdateBy()).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetAndSetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "OrgCode";

        // When
        basicbasedevicelog.setOperatorOrgCode(expectedOperatorOrgCode);

        // Then
        assertThat(basicbasedevicelog.getOperatorOrgCode()).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetAndSetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SelfOrgCode";

        // When
        basicbasedevicelog.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // Then
        assertThat(basicbasedevicelog.getOperatorSelfOrgCode()).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String expectedTenantId = "Tenant123";

        // When
        basicbasedevicelog.setTenantId(expectedTenantId);

        // Then
        assertThat(basicbasedevicelog.getTenantId()).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetAndSetUrl() {
        // Given
        String expectedUrl = "http://example.com";

        // When
        basicbasedevicelog.setUrl(expectedUrl);

        // Then
        assertThat(basicbasedevicelog.getUrl()).isEqualTo(expectedUrl);
    }

    @Test
    void testGetAndSetMacAddress() {
        // Given
        String expectedMacAddress = "00:1B:44:11:3A:B7";

        // When
        basicbasedevicelog.setMacAddress(expectedMacAddress);

        // Then
        assertThat(basicbasedevicelog.getMacAddress()).isEqualTo(expectedMacAddress);
    }

    @Test
    void testGetAndSetTriggerTime() {
        // Given
        Long expectedTriggerTime = 123456789L;

        // When
        basicbasedevicelog.setTriggerTime(expectedTriggerTime);

        // Then
        assertThat(basicbasedevicelog.getTriggerTime()).isEqualTo(expectedTriggerTime);
    }

    @Test
    void testGetAndSetBusinessTimeStart() {
        // Given
        String expectedBusinessTimeStart = "2023-01-01";

        // When
        basicbasedevicelog.setBusinessTimeStart(expectedBusinessTimeStart);

        // Then
        assertThat(basicbasedevicelog.getBusinessTimeStart()).isEqualTo(expectedBusinessTimeStart);
    }

    @Test
    void testGetAndSetBusinessTimeEnd() {
        // Given
        String expectedBusinessTimeEnd = "2023-12-31";

        // When
        basicbasedevicelog.setBusinessTimeEnd(expectedBusinessTimeEnd);

        // Then
        assertThat(basicbasedevicelog.getBusinessTimeEnd()).isEqualTo(expectedBusinessTimeEnd);
    }
}
