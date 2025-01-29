package com.matariky.commonservice.printlog.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBasePrintLogTest {

    @InjectMocks
    private BasicBasePrintLog basicbaseprintlog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetGoodsCode() {
        // Given
        String goodsCode = "12345";

        // When
        basicbaseprintlog.setGoodsCode(goodsCode);

        // Then
        assertThat(basicbaseprintlog.getGoodsCode()).isEqualTo(goodsCode);
    }

    @Test
    void testGetAndSetBusinessTimeStart() {
        // Given
        String businessTimeStart = "2023-01-01";

        // When
        basicbaseprintlog.setBusinessTimeStart(businessTimeStart);

        // Then
        assertThat(basicbaseprintlog.getBusinessTimeStart()).isEqualTo(businessTimeStart);
    }

    @Test
    void testGetAndSetBusinessTimeEnd() {
        // Given
        String businessTimeEnd = "2023-12-31";

        // When
        basicbaseprintlog.setBusinessTimeEnd(businessTimeEnd);

        // Then
        assertThat(basicbaseprintlog.getBusinessTimeEnd()).isEqualTo(businessTimeEnd);
    }

    @Test
    void testGetAndSetPrintTimeStart() {
        // Given
        String printTimeStart = "2023-01-01";

        // When
        basicbaseprintlog.setPrintTimeStart(printTimeStart);

        // Then
        assertThat(basicbaseprintlog.getPrintTimeStart()).isEqualTo(printTimeStart);
    }

    @Test
    void testGetAndSetPrintTimeEnd() {
        // Given
        String printTimeEnd = "2023-12-31";

        // When
        basicbaseprintlog.setPrintTimeEnd(printTimeEnd);

        // Then
        assertThat(basicbaseprintlog.getPrintTimeEnd()).isEqualTo(printTimeEnd);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long id = 1L;

        // When
        basicbaseprintlog.setId(id);

        // Then
        assertThat(basicbaseprintlog.getId()).isEqualTo(id);
    }

    @Test
    void testGetAndSetSystemVersionNumber() {
        // Given
        String systemVersionNumber = "v1.0";

        // When
        basicbaseprintlog.setSystemVersionNumber(systemVersionNumber);

        // Then
        assertThat(basicbaseprintlog.getSystemVersionNumber()).isEqualTo(systemVersionNumber);
    }

    @Test
    void testGetAndSetPrintTaskNumber() {
        // Given
        String printTaskNumber = "PT12345";

        // When
        basicbaseprintlog.setPrintTaskNumber(printTaskNumber);

        // Then
        assertThat(basicbaseprintlog.getPrintTaskNumber()).isEqualTo(printTaskNumber);
    }

    @Test
    void testGetAndSetPrintDetailId() {
        // Given
        String printDetailId = "PD12345";

        // When
        basicbaseprintlog.setPrintDetailId(printDetailId);

        // Then
        assertThat(basicbaseprintlog.getPrintDetailId()).isEqualTo(printDetailId);
    }

    @Test
    void testGetAndSetPrintTime() {
        // Given
        Long printTime = 123456789L;

        // When
        basicbaseprintlog.setPrintTime(printTime);

        // Then
        assertThat(basicbaseprintlog.getPrintTime()).isEqualTo(printTime);
    }

    @Test
    void testGetAndSetPrintStatus() {
        // Given
        Integer printStatus = 1;

        // When
        basicbaseprintlog.setPrintStatus(printStatus);

        // Then
        assertThat(basicbaseprintlog.getPrintStatus()).isEqualTo(printStatus);
    }

    @Test
    void testGetAndSetSku() {
        // Given
        String sku = "SKU12345";

        // When
        basicbaseprintlog.setSku(sku);

        // Then
        assertThat(basicbaseprintlog.getSku()).isEqualTo(sku);
    }

    @Test
    void testGetAndSetPrintContent() {
        // Given
        String printContent = "Content";

        // When
        basicbaseprintlog.setPrintContent(printContent);

        // Then
        assertThat(basicbaseprintlog.getPrintContent()).isEqualTo(printContent);
    }

    @Test
    void testGetAndSetRfidSetting() {
        // Given
        String rfidSetting = "RFID12345";

        // When
        basicbaseprintlog.setRfidSetting(rfidSetting);

        // Then
        assertThat(basicbaseprintlog.getRfidSetting()).isEqualTo(rfidSetting);
    }

    @Test
    void testGetAndSetDeviceCode() {
        // Given
        String deviceCode = "DC12345";

        // When
        basicbaseprintlog.setDeviceCode(deviceCode);

        // Then
        assertThat(basicbaseprintlog.getDeviceCode()).isEqualTo(deviceCode);
    }

    @Test
    void testGetAndSetMacAddress() {
        // Given
        String macAddress = "00:1B:44:11:3A:B7";

        // When
        basicbaseprintlog.setMacAddress(macAddress);

        // Then
        assertThat(basicbaseprintlog.getMacAddress()).isEqualTo(macAddress);
    }

    @Test
    void testGetAndSetAccessAccount() {
        // Given
        String accessAccount = "user123";

        // When
        basicbaseprintlog.setAccessAccount(accessAccount);

        // Then
        assertThat(basicbaseprintlog.getAccessAccount()).isEqualTo(accessAccount);
    }

    @Test
    void testGetAndSetServerIp() {
        // Given
        String serverIp = "192.168.1.1";

        // When
        basicbaseprintlog.setServerIp(serverIp);

        // Then
        assertThat(basicbaseprintlog.getServerIp()).isEqualTo(serverIp);
    }

    @Test
    void testGetAndSetCreateTime() {
        // Given
        Long createTime = 123456789L;

        // When
        basicbaseprintlog.setCreateTime(createTime);

        // Then
        assertThat(basicbaseprintlog.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        // Given
        Long updateTime = 987654321L;

        // When
        basicbaseprintlog.setUpdateTime(updateTime);

        // Then
        assertThat(basicbaseprintlog.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testGetAndSetDeleteTime() {
        // Given
        Long deleteTime = 123456789L;

        // When
        basicbaseprintlog.setDeleteTime(deleteTime);

        // Then
        assertThat(basicbaseprintlog.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testGetAndSetCreateBy() {
        // Given
        Long createBy = 1L;

        // When
        basicbaseprintlog.setCreateBy(createBy);

        // Then
        assertThat(basicbaseprintlog.getCreateBy()).isEqualTo(createBy);
    }

    @Test
    void testGetAndSetUpdateBy() {
        // Given
        Long updateBy = 2L;

        // When
        basicbaseprintlog.setUpdateBy(updateBy);

        // Then
        assertThat(basicbaseprintlog.getUpdateBy()).isEqualTo(updateBy);
    }

    @Test
    void testGetAndSetOperatorOrgCode() {
        // Given
        String operatorOrgCode = "ORG123";

        // When
        basicbaseprintlog.setOperatorOrgCode(operatorOrgCode);

        // Then
        assertThat(basicbaseprintlog.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testGetAndSetOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "SELFORG123";

        // When
        basicbaseprintlog.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicbaseprintlog.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String tenantId = "TENANT123";

        // When
        basicbaseprintlog.setTenantId(tenantId);

        // Then
        assertThat(basicbaseprintlog.getTenantId()).isEqualTo(tenantId);
    }
}
