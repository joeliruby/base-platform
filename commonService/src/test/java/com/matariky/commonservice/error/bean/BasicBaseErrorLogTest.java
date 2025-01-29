package com.matariky.commonservice.error.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseErrorLogTest {

    @InjectMocks
    private BasicBaseErrorLog basicbaseerrorlog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long expectedId = 1L;

        // When
        basicbaseerrorlog.setId(expectedId);

        // Then
        assertThat(basicbaseerrorlog.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetAndSetSystemVersionNumber() {
        // Given
        String expectedSystemVersionNumber = "1.0";

        // When
        basicbaseerrorlog.setSystemVersionNumber(expectedSystemVersionNumber);

        // Then
        assertThat(basicbaseerrorlog.getSystemVersionNumber()).isEqualTo(expectedSystemVersionNumber);
    }

    @Test
    void testGetAndSetDeviceType() {
        // Given
        String expectedDeviceType = "Mobile";

        // When
        basicbaseerrorlog.setDeviceType(expectedDeviceType);

        // Then
        assertThat(basicbaseerrorlog.getDeviceType()).isEqualTo(expectedDeviceType);
    }

    @Test
    void testGetAndSetBusinessModule() {
        // Given
        String expectedBusinessModule = "Sales";

        // When
        basicbaseerrorlog.setBusinessModule(expectedBusinessModule);

        // Then
        assertThat(basicbaseerrorlog.getBusinessModule()).isEqualTo(expectedBusinessModule);
    }

    @Test
    void testGetAndSetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;

        // When
        basicbaseerrorlog.setBusinessTime(expectedBusinessTime);

        // Then
        assertThat(basicbaseerrorlog.getBusinessTime()).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testGetAndSetErrorLevel() {
        // Given
        Integer expectedErrorLevel = 1;

        // When
        basicbaseerrorlog.setErrorLevel(expectedErrorLevel);

        // Then
        assertThat(basicbaseerrorlog.getErrorLevel()).isEqualTo(expectedErrorLevel);
    }

    @Test
    void testGetAndSetApiName() {
        // Given
        String expectedApiName = "getUser";

        // When
        basicbaseerrorlog.setApiName(expectedApiName);

        // Then
        assertThat(basicbaseerrorlog.getApiName()).isEqualTo(expectedApiName);
    }

    @Test
    void testGetAndSetUrl() {
        // Given
        String expectedUrl = "http://example.com";

        // When
        basicbaseerrorlog.setUrl(expectedUrl);

        // Then
        assertThat(basicbaseerrorlog.getUrl()).isEqualTo(expectedUrl);
    }

    @Test
    void testGetAndSetErrorContent() {
        // Given
        String expectedErrorContent = "Error occurred";

        // When
        basicbaseerrorlog.setErrorContent(expectedErrorContent);

        // Then
        assertThat(basicbaseerrorlog.getErrorContent()).isEqualTo(expectedErrorContent);
    }

    @Test
    void testGetAndSetParam() {
        // Given
        String expectedParam = "param1";

        // When
        basicbaseerrorlog.setParam(expectedParam);

        // Then
        assertThat(basicbaseerrorlog.getParam()).isEqualTo(expectedParam);
    }

    @Test
    void testGetAndSetPhysicalAddress() {
        // Given
        String expectedPhysicalAddress = "123 Main St";

        // When
        basicbaseerrorlog.setPhysicalAddress(expectedPhysicalAddress);

        // Then
        assertThat(basicbaseerrorlog.getPhysicalAddress()).isEqualTo(expectedPhysicalAddress);
    }

    @Test
    void testGetAndSetAccessAccount() {
        // Given
        String expectedAccessAccount = "user123";

        // When
        basicbaseerrorlog.setAccessAccount(expectedAccessAccount);

        // Then
        assertThat(basicbaseerrorlog.getAccessAccount()).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testGetAndSetServerIp() {
        // Given
        String expectedServerIp = "192.168.1.1";

        // When
        basicbaseerrorlog.setServerIp(expectedServerIp);

        // Then
        assertThat(basicbaseerrorlog.getServerIp()).isEqualTo(expectedServerIp);
    }

    @Test
    void testGetAndSetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;

        // When
        basicbaseerrorlog.setCreateTime(expectedCreateTime);

        // Then
        assertThat(basicbaseerrorlog.getCreateTime()).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;

        // When
        basicbaseerrorlog.setUpdateTime(expectedUpdateTime);

        // Then
        assertThat(basicbaseerrorlog.getUpdateTime()).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetAndSetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;

        // When
        basicbaseerrorlog.setDeleteTime(expectedDeleteTime);

        // Then
        assertThat(basicbaseerrorlog.getDeleteTime()).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetAndSetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;

        // When
        basicbaseerrorlog.setCreateBy(expectedCreateBy);

        // Then
        assertThat(basicbaseerrorlog.getCreateBy()).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetAndSetUpdateBy() {
        // Given
        Long expectedUpdateBy = 2L;

        // When
        basicbaseerrorlog.setUpdateBy(expectedUpdateBy);

        // Then
        assertThat(basicbaseerrorlog.getUpdateBy()).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetAndSetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG123";

        // When
        basicbaseerrorlog.setOperatorOrgCode(expectedOperatorOrgCode);

        // Then
        assertThat(basicbaseerrorlog.getOperatorOrgCode()).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetAndSetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELF123";

        // When
        basicbaseerrorlog.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // Then
        assertThat(basicbaseerrorlog.getOperatorSelfOrgCode()).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";

        // When
        basicbaseerrorlog.setTenantId(expectedTenantId);

        // Then
        assertThat(basicbaseerrorlog.getTenantId()).isEqualTo(expectedTenantId);
    }
}
