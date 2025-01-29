package com.matariky.commonservice.loginlog.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonLoginLogTest {

    @InjectMocks
    private CommonLoginLog commonLoginLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long expectedId = 1L;

        // When
        commonLoginLog.setId(expectedId);

        // Then
        assertThat(commonLoginLog.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetAndSetLoginTime() {
        // Given
        Long expectedLoginTime = System.currentTimeMillis();

        // When
        commonLoginLog.setLoginTime(expectedLoginTime);

        // Then
        assertThat(commonLoginLog.getLoginTime()).isEqualTo(expectedLoginTime);
    }

    @Test
    void testGetAndSetUserId() {
        // Given
        Long expectedUserId = 123L;

        // When
        commonLoginLog.setUserId(expectedUserId);

        // Then
        assertThat(commonLoginLog.getUserId()).isEqualTo(expectedUserId);
    }

    @Test
    void testGetAndSetRealName() {
        // Given
        String expectedRealName = "John Doe";

        // When
        commonLoginLog.setRealName(expectedRealName);

        // Then
        assertThat(commonLoginLog.getRealName()).isEqualTo(expectedRealName);
    }

    @Test
    void testGetAndSetIp() {
        // Given
        String expectedIp = "192.168.1.1";

        // When
        commonLoginLog.setIp(expectedIp);

        // Then
        assertThat(commonLoginLog.getIp()).isEqualTo(expectedIp);
    }

    @Test
    void testGetAndSetClient() {
        // Given
        String expectedClient = "Mozilla";

        // When
        commonLoginLog.setClient(expectedClient);

        // Then
        assertThat(commonLoginLog.getClient()).isEqualTo(expectedClient);
    }

    @Test
    void testGetAndSetApplicationId() {
        // Given
        Long expectedApplicationId = 456L;

        // When
        commonLoginLog.setApplicationId(expectedApplicationId);

        // Then
        assertThat(commonLoginLog.getApplicationId()).isEqualTo(expectedApplicationId);
    }

    @Test
    void testGetAndSetApplicationName() {
        // Given
        String expectedApplicationName = "TestApp";

        // When
        commonLoginLog.setApplicationName(expectedApplicationName);

        // Then
        assertThat(commonLoginLog.getApplicationName()).isEqualTo(expectedApplicationName);
    }

    @Test
    void testGetAndSetIsSuccess() {
        // Given
        Boolean expectedIsSuccess = true;

        // When
        commonLoginLog.setIsSuccess(expectedIsSuccess);

        // Then
        assertThat(commonLoginLog.getIsSuccess()).isEqualTo(expectedIsSuccess);
    }

    @Test
    void testGetAndSetFailMessage() {
        // Given
        String expectedFailMessage = "Login failed";

        // When
        commonLoginLog.setFailMessage(expectedFailMessage);

        // Then
        assertThat(commonLoginLog.getFailMessage()).isEqualTo(expectedFailMessage);
    }

    @Test
    void testGetAndSetAccountName() {
        // Given
        String expectedAccountName = "johndoe";

        // When
        commonLoginLog.setAccountName(expectedAccountName);

        // Then
        assertThat(commonLoginLog.getAccountName()).isEqualTo(expectedAccountName);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String expectedTenantId = "tenant123";

        // When
        commonLoginLog.setTenantId(expectedTenantId);

        // Then
        assertThat(commonLoginLog.getTenantId()).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetAndSetTenantName() {
        // Given
        String expectedTenantName = "TenantName";

        // When
        commonLoginLog.setTenantName(expectedTenantName);

        // Then
        assertThat(commonLoginLog.getTenantName()).isEqualTo(expectedTenantName);
    }

    @Test
    void testGetAndSetLoginAddress() {
        // Given
        String expectedLoginAddress = "123 Main St";

        // When
        commonLoginLog.setLoginAddress(expectedLoginAddress);

        // Then
        assertThat(commonLoginLog.getLoginAddress()).isEqualTo(expectedLoginAddress);
    }
}
