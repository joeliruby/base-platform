package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TenantTest {

    @InjectMocks
    private Tenant tenant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        tenant.setId(expectedId);

        // When
        Long actualId = tenant.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTenantCode() {
        // Given
        String expectedTenantCode = "T001";
        tenant.setTenantCode(expectedTenantCode);

        // When
        String actualTenantCode = tenant.getTenantCode();

        // Then
        assertThat(actualTenantCode).isEqualTo(expectedTenantCode);
    }

    @Test
    void testGetTenantName() {
        // Given
        String expectedTenantName = "TenantName";
        tenant.setTenantName(expectedTenantName);

        // When
        String actualTenantName = tenant.getTenantName();

        // Then
        assertThat(actualTenantName).isEqualTo(expectedTenantName);
    }

    @Test
    void testIsActive() {
        // Given
        Boolean expectedIsActive = true;
        tenant.setIsActive(expectedIsActive);

        // When
        Boolean actualIsActive = tenant.getIsActive();

        // Then
        assertThat(actualIsActive).isEqualTo(expectedIsActive);
    }

    @Test
    void testGetDomainName() {
        // Given
        String expectedDomainName = "example.com";
        tenant.setDomainName(expectedDomainName);

        // When
        String actualDomainName = tenant.getDomainName();

        // Then
        assertThat(actualDomainName).isEqualTo(expectedDomainName);
    }

    // Add more test methods for other getters and setters in Tenant
}
