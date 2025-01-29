package com.matariky.commonservice.printlog.excel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PrintLogExcelVOTest {

    @InjectMocks
    private PrintLogExcelVO printlogexcelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSystemVersionNumber() {
        // Given
        String expectedValue = "1.0.0";
        printlogexcelvo.setSystemVersionNumber(expectedValue);

        // When
        String actualValue = printlogexcelvo.getSystemVersionNumber();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetBusinessTime() {
        // Given
        Long expectedValue = 1627849200000L;
        printlogexcelvo.setBusinessTime(expectedValue);

        // When
        Long actualValue = printlogexcelvo.getBusinessTime();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetPrintTaskNumber() {
        // Given
        String expectedValue = "PT123456";
        printlogexcelvo.setPrintTaskNumber(expectedValue);

        // When
        String actualValue = printlogexcelvo.getPrintTaskNumber();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetPrintDetailId() {
        // Given
        String expectedValue = "PD78910";
        printlogexcelvo.setPrintDetailId(expectedValue);

        // When
        String actualValue = printlogexcelvo.getPrintDetailId();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetPrintStatus() {
        // Given
        Integer expectedValue = 1;
        printlogexcelvo.setPrintStatus(expectedValue);

        // When
        Integer actualValue = printlogexcelvo.getPrintStatus();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetSku() {
        // Given
        String expectedValue = "SKU12345";
        printlogexcelvo.setSku(expectedValue);

        // When
        String actualValue = printlogexcelvo.getSku();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetPrintContent() {
        // Given
        String expectedValue = "Print Content Example";
        printlogexcelvo.setPrintContent(expectedValue);

        // When
        String actualValue = printlogexcelvo.getPrintContent();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetReturnTid() {
        // Given
        String expectedValue = "TID12345";
        printlogexcelvo.setReturnTid(expectedValue);

        // When
        String actualValue = printlogexcelvo.getReturnTid();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetRfidSetting() {
        // Given
        String expectedValue = "RFID Setting Example";
        printlogexcelvo.setRfidSetting(expectedValue);

        // When
        String actualValue = printlogexcelvo.getRfidSetting();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetDeviceCode() {
        // Given
        String expectedValue = "DC12345";
        printlogexcelvo.setDeviceCode(expectedValue);

        // When
        String actualValue = printlogexcelvo.getDeviceCode();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetMacAddress() {
        // Given
        String expectedValue = "00:1B:44:11:3A:B7";
        printlogexcelvo.setMacAddress(expectedValue);

        // When
        String actualValue = printlogexcelvo.getMacAddress();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetAccessAccount() {
        // Given
        String expectedValue = "accessAccount123";
        printlogexcelvo.setAccessAccount(expectedValue);

        // When
        String actualValue = printlogexcelvo.getAccessAccount();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetServerIp() {
        // Given
        String expectedValue = "192.168.1.1";
        printlogexcelvo.setServerIp(expectedValue);

        // When
        String actualValue = printlogexcelvo.getServerIp();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedValue = 1627849200000L;
        printlogexcelvo.setCreateTime(expectedValue);

        // When
        Long actualValue = printlogexcelvo.getCreateTime();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}
