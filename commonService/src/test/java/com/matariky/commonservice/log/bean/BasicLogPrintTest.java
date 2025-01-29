package com.matariky.commonservice.log.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicLogPrintTest {

    @InjectMocks
    private BasicLogPrint basiclogprint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basiclogprint.setId(expectedId);

        // When
        Long actualId = basiclogprint.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetSystemVersion() {
        // Given
        String expectedSystemVersion = "1.0";
        basiclogprint.setSystemVersion(expectedSystemVersion);

        // When
        String actualSystemVersion = basiclogprint.getSystemVersion();

        // Then
        assertThat(actualSystemVersion).isEqualTo(expectedSystemVersion);
    }

    @Test
    void testGetPrintTaskCode() {
        // Given
        String expectedPrintTaskCode = "TASK123";
        basiclogprint.setPrintTaskCode(expectedPrintTaskCode);

        // When
        String actualPrintTaskCode = basiclogprint.getPrintTaskCode();

        // Then
        assertThat(actualPrintTaskCode).isEqualTo(expectedPrintTaskCode);
    }

    @Test
    void testGetAccessInterface() {
        // Given
        String expectedAccessInterface = "INTERFACE1";
        basiclogprint.setAccessInterface(expectedAccessInterface);

        // When
        String actualAccessInterface = basiclogprint.getAccessInterface();

        // Then
        assertThat(actualAccessInterface).isEqualTo(expectedAccessInterface);
    }

    @Test
    void testGetOperateButton() {
        // Given
        String expectedOperateButton = "BUTTON1";
        basiclogprint.setOperateButton(expectedOperateButton);

        // When
        String actualOperateButton = basiclogprint.getOperateButton();

        // Then
        assertThat(actualOperateButton).isEqualTo(expectedOperateButton);
    }

    @Test
    void testGetPrintContent() {
        // Given
        String expectedPrintContent = "CONTENT1";
        basiclogprint.setPrintContent(expectedPrintContent);

        // When
        String actualPrintContent = basiclogprint.getPrintContent();

        // Then
        assertThat(actualPrintContent).isEqualTo(expectedPrintContent);
    }

    @Test
    void testGetReturnTid() {
        // Given
        String expectedReturnTid = "TID123";
        basiclogprint.setReturnTid(expectedReturnTid);

        // When
        String actualReturnTid = basiclogprint.getReturnTid();

        // Then
        assertThat(actualReturnTid).isEqualTo(expectedReturnTid);
    }

    @Test
    void testGetPrintStatus() {
        // Given
        Integer expectedPrintStatus = 1;
        basiclogprint.setPrintStatus(expectedPrintStatus);

        // When
        Integer actualPrintStatus = basiclogprint.getPrintStatus();

        // Then
        assertThat(actualPrintStatus).isEqualTo(expectedPrintStatus);
    }

    @Test
    void testGetFailReason() {
        // Given
        String expectedFailReason = "FAIL_REASON";
        basiclogprint.setFailReason(expectedFailReason);

        // When
        String actualFailReason = basiclogprint.getFailReason();

        // Then
        assertThat(actualFailReason).isEqualTo(expectedFailReason);
    }

    @Test
    void testGetRfidSet() {
        // Given
        String expectedRfidSet = "RFID_SET";
        basiclogprint.setRfidSet(expectedRfidSet);

        // When
        String actualRfidSet = basiclogprint.getRfidSet();

        // Then
        assertThat(actualRfidSet).isEqualTo(expectedRfidSet);
    }

    @Test
    void testGetDeviceId() {
        // Given
        Long expectedDeviceId = 123L;
        basiclogprint.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = basiclogprint.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testGetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;
        basiclogprint.setBusinessTime(expectedBusinessTime);

        // When
        Long actualBusinessTime = basiclogprint.getBusinessTime();

        // Then
        assertThat(actualBusinessTime).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testGetAccessAccount() {
        // Given
        String expectedAccessAccount = "ACCOUNT1";
        basiclogprint.setAccessAccount(expectedAccessAccount);

        // When
        String actualAccessAccount = basiclogprint.getAccessAccount();

        // Then
        assertThat(actualAccessAccount).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testGetOutIp() {
        // Given
        String expectedOutIp = "192.168.1.1";
        basiclogprint.setOutIp(expectedOutIp);

        // When
        String actualOutIp = basiclogprint.getOutIp();

        // Then
        assertThat(actualOutIp).isEqualTo(expectedOutIp);
    }

    @Test
    void testGetLoginAddress() {
        // Given
        String expectedLoginAddress = "ADDRESS1";
        basiclogprint.setLoginAddress(expectedLoginAddress);

        // When
        String actualLoginAddress = basiclogprint.getLoginAddress();

        // Then
        assertThat(actualLoginAddress).isEqualTo(expectedLoginAddress);
    }

    @Test
    void testGetLogTime() {
        // Given
        Long expectedLogTime = 123456789L;
        basiclogprint.setLogTime(expectedLogTime);

        // When
        Long actualLogTime = basiclogprint.getLogTime();

        // Then
        assertThat(actualLogTime).isEqualTo(expectedLogTime);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "REMARK1";
        basiclogprint.setRemark(expectedRemark);

        // When
        String actualRemark = basiclogprint.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basiclogprint.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basiclogprint.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 123456789L;
        basiclogprint.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basiclogprint.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;
        basiclogprint.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basiclogprint.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 123L;
        basiclogprint.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basiclogprint.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 123L;
        basiclogprint.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basiclogprint.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG_CODE1";
        basiclogprint.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basiclogprint.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELF_ORG_CODE1";
        basiclogprint.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basiclogprint.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT1";
        basiclogprint.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basiclogprint.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }
}
