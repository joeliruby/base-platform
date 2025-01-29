package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceExeclVOTest {

    @InjectMocks
    private BasicBaseDeviceExeclVO basicbasedeviceexeclvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeName() {
        // Given
        String expectedTypeName = "Router";
        basicbasedeviceexeclvo.setTypeName(expectedTypeName);

        // When
        String actualTypeName = basicbasedeviceexeclvo.getTypeName();

        // Then
        assertThat(actualTypeName).isEqualTo(expectedTypeName);
    }

    @Test
    void testDeviceCode() {
        // Given
        String expectedDeviceCode = "DVC12345";
        basicbasedeviceexeclvo.setDeviceCode(expectedDeviceCode);

        // When
        String actualDeviceCode = basicbasedeviceexeclvo.getDeviceCode();

        // Then
        assertThat(actualDeviceCode).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testDeviceDbm() {
        // Given
        String expectedDeviceDbm = "20dBm";
        basicbasedeviceexeclvo.setDeviceDbm(expectedDeviceDbm);

        // When
        String actualDeviceDbm = basicbasedeviceexeclvo.getDeviceDbm();

        // Then
        assertThat(actualDeviceDbm).isEqualTo(expectedDeviceDbm);
    }

    @Test
    void testDeviceDescribe() {
        // Given
        String expectedDeviceDescribe = "High power router";
        basicbasedeviceexeclvo.setDeviceDescribe(expectedDeviceDescribe);

        // When
        String actualDeviceDescribe = basicbasedeviceexeclvo.getDeviceDescribe();

        // Then
        assertThat(actualDeviceDescribe).isEqualTo(expectedDeviceDescribe);
    }

    @Test
    void testDeviceIp() {
        // Given
        String expectedDeviceIp = "192.168.1.1";
        basicbasedeviceexeclvo.setDeviceIp(expectedDeviceIp);

        // When
        String actualDeviceIp = basicbasedeviceexeclvo.getDeviceIp();

        // Then
        assertThat(actualDeviceIp).isEqualTo(expectedDeviceIp);
    }

    @Test
    void testDeviceMac() {
        // Given
        String expectedDeviceMac = "00:1A:2B:3C:4D:5E";
        basicbasedeviceexeclvo.setDeviceMac(expectedDeviceMac);

        // When
        String actualDeviceMac = basicbasedeviceexeclvo.getDeviceMac();

        // Then
        assertThat(actualDeviceMac).isEqualTo(expectedDeviceMac);
    }

    @Test
    void testLongitude() {
        // Given
        String expectedLongitude = "123.456";
        basicbasedeviceexeclvo.setLongitude(expectedLongitude);

        // When
        String actualLongitude = basicbasedeviceexeclvo.getLongitude();

        // Then
        assertThat(actualLongitude).isEqualTo(expectedLongitude);
    }

    @Test
    void testLatitude() {
        // Given
        String expectedLatitude = "78.910";
        basicbasedeviceexeclvo.setLatitude(expectedLatitude);

        // When
        String actualLatitude = basicbasedeviceexeclvo.getLatitude();

        // Then
        assertThat(actualLatitude).isEqualTo(expectedLatitude);
    }

    @Test
    void testInstallAddress() {
        // Given
        String expectedInstallAddress = "123 Main St";
        basicbasedeviceexeclvo.setInstallAddress(expectedInstallAddress);

        // When
        String actualInstallAddress = basicbasedeviceexeclvo.getInstallAddress();

        // Then
        assertThat(actualInstallAddress).isEqualTo(expectedInstallAddress);
    }
}
