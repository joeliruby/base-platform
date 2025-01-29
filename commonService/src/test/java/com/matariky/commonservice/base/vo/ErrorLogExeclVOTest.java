package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ErrorLogExeclVOTest {

    @InjectMocks
    private ErrorLogExeclVO errorlogexeclvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;
        errorlogexeclvo.setBusinessTime(expectedBusinessTime);

        // When
        Long actualBusinessTime = errorlogexeclvo.getBusinessTime();

        // Then
        assertThat(actualBusinessTime).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testGetSystemVersionNumber() {
        // Given
        String expectedVersionNumber = "1.0.0";
        errorlogexeclvo.setSystemVersionNumber(expectedVersionNumber);

        // When
        String actualVersionNumber = errorlogexeclvo.getSystemVersionNumber();

        // Then
        assertThat(actualVersionNumber).isEqualTo(expectedVersionNumber);
    }

    @Test
    void testGetDeviceType() {
        // Given
        String expectedDeviceType = "Mobile";
        errorlogexeclvo.setDeviceType(expectedDeviceType);

        // When
        String actualDeviceType = errorlogexeclvo.getDeviceType();

        // Then
        assertThat(actualDeviceType).isEqualTo(expectedDeviceType);
    }

    @Test
    void testGetBusinessModule() {
        // Given
        String expectedBusinessModule = "ModuleA";
        errorlogexeclvo.setBusinessModule(expectedBusinessModule);

        // When
        String actualBusinessModule = errorlogexeclvo.getBusinessModule();

        // Then
        assertThat(actualBusinessModule).isEqualTo(expectedBusinessModule);
    }

    @Test
    void testGetUrl() {
        // Given
        String expectedUrl = "http://example.com";
        errorlogexeclvo.setUrl(expectedUrl);

        // When
        String actualUrl = errorlogexeclvo.getUrl();

        // Then
        assertThat(actualUrl).isEqualTo(expectedUrl);
    }

    @Test
    void testGetApiName() {
        // Given
        String expectedApiName = "getApi";
        errorlogexeclvo.setApiName(expectedApiName);

        // When
        String actualApiName = errorlogexeclvo.getApiName();

        // Then
        assertThat(actualApiName).isEqualTo(expectedApiName);
    }

    @Test
    void testGetParam() {
        // Given
        String expectedParam = "paramValue";
        errorlogexeclvo.setParam(expectedParam);

        // When
        String actualParam = errorlogexeclvo.getParam();

        // Then
        assertThat(actualParam).isEqualTo(expectedParam);
    }

    @Test
    void testGetErrorContent() {
        // Given
        String expectedErrorContent = "Error occurred";
        errorlogexeclvo.setErrorContent(expectedErrorContent);

        // When
        String actualErrorContent = errorlogexeclvo.getErrorContent();

        // Then
        assertThat(actualErrorContent).isEqualTo(expectedErrorContent);
    }

    @Test
    void testGetErrorLevel() {
        // Given
        Integer expectedErrorLevel = 1;
        errorlogexeclvo.setErrorLevel(expectedErrorLevel);

        // When
        Integer actualErrorLevel = errorlogexeclvo.getErrorLevel();

        // Then
        assertThat(actualErrorLevel).isEqualTo(expectedErrorLevel);
    }

    @Test
    void testGetAccessAccount() {
        // Given
        String expectedAccessAccount = "user123";
        errorlogexeclvo.setAccessAccount(expectedAccessAccount);

        // When
        String actualAccessAccount = errorlogexeclvo.getAccessAccount();

        // Then
        assertThat(actualAccessAccount).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testGetServerIp() {
        // Given
        String expectedServerIp = "192.168.1.1";
        errorlogexeclvo.setServerIp(expectedServerIp);

        // When
        String actualServerIp = errorlogexeclvo.getServerIp();

        // Then
        assertThat(actualServerIp).isEqualTo(expectedServerIp);
    }

    @Test
    void testGetPhysicalAddress() {
        // Given
        String expectedPhysicalAddress = "123 Main St";
        errorlogexeclvo.setPhysicalAddress(expectedPhysicalAddress);

        // When
        String actualPhysicalAddress = errorlogexeclvo.getPhysicalAddress();

        // Then
        assertThat(actualPhysicalAddress).isEqualTo(expectedPhysicalAddress);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 987654321L;
        errorlogexeclvo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = errorlogexeclvo.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }
}
