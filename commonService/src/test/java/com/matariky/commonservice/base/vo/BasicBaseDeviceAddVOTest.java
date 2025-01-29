package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceAddVOTest {

    @InjectMocks
    private BasicBaseDeviceAddVO basicbasedeviceaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeId() {
        // Given
        Long expectedTypeId = 1L;
        basicbasedeviceaddvo.setTypeId(expectedTypeId);

        // When
        Long actualTypeId = basicbasedeviceaddvo.getTypeId();

        // Then
        assertThat(actualTypeId).isEqualTo(expectedTypeId);
    }

    @Test
    void testDeviceCode() {
        // Given
        String expectedDeviceCode = "ABC123";
        basicbasedeviceaddvo.setDeviceCode(expectedDeviceCode);

        // When
        String actualDeviceCode = basicbasedeviceaddvo.getDeviceCode();

        // Then
        assertThat(actualDeviceCode).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testDeviceDescribe() {
        // Given
        String expectedDeviceDescribe = "Test Device";
        basicbasedeviceaddvo.setDeviceDescribe(expectedDeviceDescribe);

        // When
        String actualDeviceDescribe = basicbasedeviceaddvo.getDeviceDescribe();

        // Then
        assertThat(actualDeviceDescribe).isEqualTo(expectedDeviceDescribe);
    }

    @Test
    void testDeviceDbm() {
        // Given
        String expectedDeviceDbm = "100dBm";
        basicbasedeviceaddvo.setDeviceDbm(expectedDeviceDbm);

        // When
        String actualDeviceDbm = basicbasedeviceaddvo.getDeviceDbm();

        // Then
        assertThat(actualDeviceDbm).isEqualTo(expectedDeviceDbm);
    }

    @Test
    void testDeviceIp() {
        // Given
        String expectedDeviceIp = "192.168.1.1";
        basicbasedeviceaddvo.setDeviceIp(expectedDeviceIp);

        // When
        String actualDeviceIp = basicbasedeviceaddvo.getDeviceIp();

        // Then
        assertThat(actualDeviceIp).isEqualTo(expectedDeviceIp);
    }

    @Test
    void testDeviceMac() {
        // Given
        String expectedDeviceMac = "00:1A:2B:3C:4D:5E";
        basicbasedeviceaddvo.setDeviceMac(expectedDeviceMac);

        // When
        String actualDeviceMac = basicbasedeviceaddvo.getDeviceMac();

        // Then
        assertThat(actualDeviceMac).isEqualTo(expectedDeviceMac);
    }

    @Test
    void testGisAddress() {
        // Given
        String expectedGisAddress = "123 Main St";
        basicbasedeviceaddvo.setGisAddress(expectedGisAddress);

        // When
        String actualGisAddress = basicbasedeviceaddvo.getGisAddress();

        // Then
        assertThat(actualGisAddress).isEqualTo(expectedGisAddress);
    }

    @Test
    void testGisAddressName() {
        // Given
        String expectedGisAddressName = "Main Office";
        basicbasedeviceaddvo.setGisAddressName(expectedGisAddressName);

        // When
        String actualGisAddressName = basicbasedeviceaddvo.getGisAddressName();

        // Then
        assertThat(actualGisAddressName).isEqualTo(expectedGisAddressName);
    }

    @Test
    void testInstallAddress() {
        // Given
        String expectedInstallAddress = "456 Elm St";
        basicbasedeviceaddvo.setInstallAddress(expectedInstallAddress);

        // When
        String actualInstallAddress = basicbasedeviceaddvo.getInstallAddress();

        // Then
        assertThat(actualInstallAddress).isEqualTo(expectedInstallAddress);
    }

    @Test
    void testInstallImg() {
        // Given
        String expectedInstallImg = "image.jpg";
        basicbasedeviceaddvo.setInstallImg(expectedInstallImg);

        // When
        String actualInstallImg = basicbasedeviceaddvo.getInstallImg();

        // Then
        assertThat(actualInstallImg).isEqualTo(expectedInstallImg);
    }
}
