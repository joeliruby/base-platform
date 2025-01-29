package com.matariky.commonservice.device.excel;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeviceLogExcelVOTest {

    @InjectMocks
    private DeviceLogExcelVO devicelogexcelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;
        devicelogexcelvo.setBusinessTime(expectedBusinessTime);

        // When
        Long actualBusinessTime = devicelogexcelvo.getBusinessTime();

        // Then
        assertThat(actualBusinessTime).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testGetSystemVersionNumber() {
        // Given
        String expectedSystemVersionNumber = "1.0.0";
        devicelogexcelvo.setSystemVersionNumber(expectedSystemVersionNumber);

        // When
        String actualSystemVersionNumber = devicelogexcelvo.getSystemVersionNumber();

        // Then
        assertThat(actualSystemVersionNumber).isEqualTo(expectedSystemVersionNumber);
    }

    @Test
    void testGetDeviceType() {
        // Given
        String expectedDeviceType = "Mobile";
        devicelogexcelvo.setDeviceType(expectedDeviceType);

        // When
        String actualDeviceType = devicelogexcelvo.getDeviceType();

        // Then
        assertThat(actualDeviceType).isEqualTo(expectedDeviceType);
    }

    @Test
    void testGetUrl() {
        // Given
        String expectedUrl = "http://example.com";
        devicelogexcelvo.setUrl(expectedUrl);

        // When
        String actualUrl = devicelogexcelvo.getUrl();

        // Then
        assertThat(actualUrl).isEqualTo(expectedUrl);
    }

    @Test
    void testGetApiName() {
        // Given
        String expectedApiName = "getDeviceLogs";
        devicelogexcelvo.setApiName(expectedApiName);

        // When
        String actualApiName = devicelogexcelvo.getApiName();

        // Then
        assertThat(actualApiName).isEqualTo(expectedApiName);
    }

    @Test
    void testGetBusinessModule() {
        // Given
        String expectedBusinessModule = "Logging";
        devicelogexcelvo.setBusinessModule(expectedBusinessModule);

        // When
        String actualBusinessModule = devicelogexcelvo.getBusinessModule();

        // Then
        assertThat(actualBusinessModule).isEqualTo(expectedBusinessModule);
    }

    @Test
    void testGetTriggerTime() {
        // Given
        Long expectedTriggerTime = 987654321L;
        devicelogexcelvo.setTriggerTime(expectedTriggerTime);

        // When
        Long actualTriggerTime = devicelogexcelvo.getTriggerTime();

        // Then
        assertThat(actualTriggerTime).isEqualTo(expectedTriggerTime);
    }

    @Test
    void testGetDeviceCode() {
        // Given
        String expectedDeviceCode = "DVC123";
        devicelogexcelvo.setDeviceCode(expectedDeviceCode);

        // When
        String actualDeviceCode = devicelogexcelvo.getDeviceCode();

        // Then
        assertThat(actualDeviceCode).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testGetMacAddress() {
        // Given
        String expectedMacAddress = "00:1B:44:11:3A:B7";
        devicelogexcelvo.setMacAddress(expectedMacAddress);

        // When
        String actualMacAddress = devicelogexcelvo.getMacAddress();

        // Then
        assertThat(actualMacAddress).isEqualTo(expectedMacAddress);
    }

    @Test
    void testGetPower() {
        // Given
        BigDecimal expectedPower = new BigDecimal("10.5");
        devicelogexcelvo.setPower(expectedPower);

        // When
        BigDecimal actualPower = devicelogexcelvo.getPower();

        // Then
        assertThat(actualPower).isEqualTo(expectedPower);
    }

    @Test
    void testGetCollectContent() {
        // Given
        String expectedCollectContent = "Sample Data";
        devicelogexcelvo.setCollectContent(expectedCollectContent);

        // When
        String actualCollectContent = devicelogexcelvo.getCollectContent();

        // Then
        assertThat(actualCollectContent).isEqualTo(expectedCollectContent);
    }

    @Test
    void testGetAccessAccount() {
        // Given
        String expectedAccessAccount = "user123";
        devicelogexcelvo.setAccessAccount(expectedAccessAccount);

        // When
        String actualAccessAccount = devicelogexcelvo.getAccessAccount();

        // Then
        assertThat(actualAccessAccount).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testGetServerIp() {
        // Given
        String expectedServerIp = "192.168.1.1";
        devicelogexcelvo.setServerIp(expectedServerIp);

        // When
        String actualServerIp = devicelogexcelvo.getServerIp();

        // Then
        assertThat(actualServerIp).isEqualTo(expectedServerIp);
    }

    @Test
    void testGetPhysicalAddress() {
        // Given
        String expectedPhysicalAddress = "123 Main St";
        devicelogexcelvo.setPhysicalAddress(expectedPhysicalAddress);

        // When
        String actualPhysicalAddress = devicelogexcelvo.getPhysicalAddress();

        // Then
        assertThat(actualPhysicalAddress).isEqualTo(expectedPhysicalAddress);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        devicelogexcelvo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = devicelogexcelvo.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }
}
