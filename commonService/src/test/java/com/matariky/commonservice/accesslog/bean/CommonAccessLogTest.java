package com.matariky.commonservice.accesslog.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonAccessLogTest {

    @InjectMocks
    private CommonAccessLog commonAccessLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        String id = "12345";

        // When
        commonAccessLog.setId(id);

        // Then
        assertThat(commonAccessLog.getId()).isEqualTo(id);
    }

    @Test
    void testGetAndSetAccessTime() {
        // Given
        Long accessTime = 123456789L;

        // When
        commonAccessLog.setAccessTime(accessTime);

        // Then
        assertThat(commonAccessLog.getAccessTime()).isEqualTo(accessTime);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String tenantId = "tenant123";

        // When
        commonAccessLog.setTenantId(tenantId);

        // Then
        assertThat(commonAccessLog.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testGetAndSetTenantName() {
        // Given
        String tenantName = "Tenant Name";

        // When
        commonAccessLog.setTenantName(tenantName);

        // Then
        assertThat(commonAccessLog.getTenantName()).isEqualTo(tenantName);
    }

    @Test
    void testGetAndSetRequestMethod() {
        // Given
        String requestMethod = "GET";

        // When
        commonAccessLog.setRequestMethod(requestMethod);

        // Then
        assertThat(commonAccessLog.getRequestMethod()).isEqualTo(requestMethod);
    }

    @Test
    void testGetAndSetRequestStatus() {
        // Given
        Integer requestStatus = 200;

        // When
        commonAccessLog.setRequestStatus(requestStatus);

        // Then
        assertThat(commonAccessLog.getRequestStatus()).isEqualTo(requestStatus);
    }

    @Test
    void testGetAndSetClient() {
        // Given
        String client = "Client";

        // When
        commonAccessLog.setClient(client);

        // Then
        assertThat(commonAccessLog.getClient()).isEqualTo(client);
    }

    @Test
    void testGetAndSetClientIp() {
        // Given
        String clientIp = "192.168.1.1";

        // When
        commonAccessLog.setClientIp(clientIp);

        // Then
        assertThat(commonAccessLog.getClientIp()).isEqualTo(clientIp);
    }

    @Test
    void testGetAndSetClientAddress() {
        // Given
        String clientAddress = "123 Main St";

        // When
        commonAccessLog.setClientAddress(clientAddress);

        // Then
        assertThat(commonAccessLog.getClientAddress()).isEqualTo(clientAddress);
    }

    @Test
    void testGetAndSetResponseBody() {
        // Given
        String responseBody = "Response Body";

        // When
        commonAccessLog.setResponseBody(responseBody);

        // Then
        assertThat(commonAccessLog.getResponseBody()).isEqualTo(responseBody);
    }

    @Test
    void testGetAndSetAccount() {
        // Given
        String account = "Account";

        // When
        commonAccessLog.setAccount(account);

        // Then
        assertThat(commonAccessLog.getAccount()).isEqualTo(account);
    }

    @Test
    void testGetAndSetRealName() {
        // Given
        String realName = "Real Name";

        // When
        commonAccessLog.setRealName(realName);

        // Then
        assertThat(commonAccessLog.getRealName()).isEqualTo(realName);
    }

    @Test
    void testGetAndSetUserId() {
        // Given
        Long userId = 123L;

        // When
        commonAccessLog.setUserId(userId);

        // Then
        assertThat(commonAccessLog.getUserId()).isEqualTo(userId);
    }

    @Test
    void testGetAndSetRequestBody() {
        // Given
        String requestBody = "Request Body";

        // When
        commonAccessLog.setRequestBody(requestBody);

        // Then
        assertThat(commonAccessLog.getRequestBody()).isEqualTo(requestBody);
    }

    @Test
    void testGetAndSetContentType() {
        // Given
        String contentType = "application/json";

        // When
        commonAccessLog.setContentType(contentType);

        // Then
        assertThat(commonAccessLog.getContentType()).isEqualTo(contentType);
    }

    @Test
    void testGetAndSetRequestUrl() {
        // Given
        String requestUrl = "http://example.com";

        // When
        commonAccessLog.setRequestUrl(requestUrl);

        // Then
        assertThat(commonAccessLog.getRequestUrl()).isEqualTo(requestUrl);
    }

    @Test
    void testGetAndSetOperatorOrgCode() {
        // Given
        String operatorOrgCode = "OrgCode";

        // When
        commonAccessLog.setOperatorOrgCode(operatorOrgCode);

        // Then
        assertThat(commonAccessLog.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testGetAndSetOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "SelfOrgCode";

        // When
        commonAccessLog.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(commonAccessLog.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testGetAndSetAccessTimeStr() {
        // Given
        String accessTimeStr = "2023-10-01T12:00:00";

        // When
        commonAccessLog.setAccessTimeStr(accessTimeStr);

        // Then
        assertThat(commonAccessLog.getAccessTimeStr()).isEqualTo(accessTimeStr);
    }

    @Test
    void testGetAndSetOperationNameString() {
        // Given
        String operationNameString = "Operation Name";

        // When
        commonAccessLog.setOperationNameString(operationNameString);

        // Then
        assertThat(commonAccessLog.getOperationNameString()).isEqualTo(operationNameString);
    }

    @Test
    void testGetAndSetOperationName() {
        // Given
        Long operationName = 123L;

        // When
        commonAccessLog.setOperationName(operationName);

        // Then
        assertThat(commonAccessLog.getOperationName()).isEqualTo(operationName);
    }
}
